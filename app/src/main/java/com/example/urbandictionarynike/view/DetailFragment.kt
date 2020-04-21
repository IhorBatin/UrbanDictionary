package com.example.urbandictionarynike.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.urbandictionarynike.databinding.FragmentDetailBinding
import com.example.urbandictionarynike.extensions.toPrettyText
import com.example.urbandictionarynike.model.Definition

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            arguments?.getParcelable<Definition>("DEFINITION")?.let { definition ->
                tvAuthor.text = definition.author
                tvDate.text = definition.writtenOn?.toPrettyText()
                tvDefinition.text = definition.definition
                tvWord.text = definition.word
                tvExamples.text = definition.example
            }
            listToolbarSearchClose.setOnClickListener { findNavController().navigateUp() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}