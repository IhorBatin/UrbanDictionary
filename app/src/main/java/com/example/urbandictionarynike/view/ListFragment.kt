package com.example.urbandictionarynike.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urbandictionarynike.R
import com.example.urbandictionarynike.adapter.WordAdapter
import com.example.urbandictionarynike.databinding.FragmentListBinding
import com.example.urbandictionarynike.extensions.isInvisible
import com.example.urbandictionarynike.extensions.isTablet
import com.example.urbandictionarynike.extensions.showView
import com.example.urbandictionarynike.model.Definition

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { (requireActivity() as MainActivity).urbanViewModel }
    private val tabletController by lazy { (activity as MainActivity).tabletController }
    private val onWordClicked = View.OnClickListener { view ->
        navigateToDetailScreen((view.tag as Definition))
    }
    private val filters = arrayOf("Date", "Likes", "Dislikes")
    private var lastQuery = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvList.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = WordAdapter(onWordClicked)
            }

            listToolbarQuery.doOnTextChanged { text, _, _, _ ->
                listToolbarSearchClear.isInvisible(text.isNullOrEmpty())
                text?.toString()?.let { query ->
                    if (query.isNotEmpty() && !viewModel.isBackSpace(lastQuery, query))
                        viewModel.queryDefinition(query)
                    lastQuery = query
                } ?: run { lastQuery = "" }
            }

            listToolbarSearchClear.setOnClickListener { listToolbarQuery.text?.clear() }

            listToolbarSearchFilter.setOnClickListener { createFilterPopup(it) }
        }
        connectObservers()
        viewModel.loadSavedDefinitions()
    }

    private fun createFilterPopup(it: View) {
        AlertDialog.Builder(it.context).apply {
            val list = viewModel.definitionListResponse.value
            setTitle(getString(R.string.sort_by))
            setSingleChoiceItems(filters, 3) { _, i ->
                val newList = when (i) {
                    0 -> list?.toMutableList()?.apply {
                        sortByDescending { definition -> definition.writtenOn }
                    }
                    1 -> list?.toMutableList()?.apply {
                        sortByDescending { definition -> definition.thumbsUp }
                    }
                    2 -> list?.toMutableList()?.apply {
                        sortByDescending { definition -> definition.thumbsDown }
                    }
                    else -> list
                }
                newList?.let { loadDefinitions(newList) }
            }
            setPositiveButton(getString(R.string.done)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
        }.create().show()
    }

    private fun connectObservers() {
        viewModel.definitionListResponse.observe(viewLifecycleOwner, Observer { definitions ->
            loadDefinitions(definitions)
            when {
                noUserTermEntered(definitions) -> showError(msg = getString(R.string.searchTermMsg))
                noDefinitionFound(definitions) -> showError(
                    msg = getString(R.string.searchTermNotFoundMsg, lastQuery)
                )
                else -> showError(false)
            }
        })
    }

    private fun loadDefinitions(definitions: List<Definition>) {
        (binding.rvList.adapter as WordAdapter).apply {
            clearDefinitions()
            addDefinitions(definitions)
        }
    }

    private fun noUserTermEntered(definitions: List<Definition>) =
        binding.listToolbarQuery.text.isNullOrEmpty() && noDefinitionFound(definitions)

    private fun noDefinitionFound(definitions: List<Definition>) = definitions.isEmpty()

    private fun showError(showError: Boolean = true, msg: String = "") {
        binding.tvError.text = msg
        binding.tvError.showView(showError)
    }

    private fun navigateToDetailScreen(definition: Definition) {
        val bundle = Bundle().apply { putParcelable("DEFINITION", definition) }
        when (isTablet()) {
            true -> tabletController.apply {
                navigateUp()
                navigate(R.id.detail_fragment, bundle)
            }
            false -> findNavController().navigate(R.id.detail_fragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}