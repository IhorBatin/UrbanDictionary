package com.example.urbandictionarynike.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionarynike.databinding.DefinitionItemBinding
import com.example.urbandictionarynike.extensions.toPrettyText
import com.example.urbandictionarynike.model.Definition

class WordAdapter(val onWordClicked: View.OnClickListener? = null) :
    RecyclerView.Adapter<WordAdapter.WordViewHolder>() {
    private val definitions = mutableListOf<Definition>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder =
        DefinitionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let { WordViewHolder(it) }

    override fun getItemCount(): Int = definitions.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(definitions[position])
    }

    /**
     * Adds list then notifies the adapter of the new definitions
     *
     * @param definitionList list of definitions to be displayed
     */
    fun addDefinitions(definitionList: List<Definition>) {
        val start = definitions.size - 1
        definitions.addAll(definitionList)
        notifyItemRangeInserted(start, definitions.size - 1)
    }

    /**
     * Clears adapter of list of definitions
     */
    fun clearDefinitions() {
        definitions.clear()
        notifyDataSetChanged()
    }

    inner class WordViewHolder(private val binding: DefinitionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(definition: Definition) {
            binding.apply {
                tvAuthor.text = definition.author
                tvDate.text = definition.writtenOn?.toPrettyText()
                tvDefinition.text = definition.definition
                tvWord.text = definition.word
                tvThumbsDown.text = definition.thumbsDown.toString()
                tvThumbsUp.text = definition.thumbsUp.toString()
                tvExamples.text = definition.example
                onWordClicked?.let { root.setOnClickListener(it) }
                root.tag = definition
            }
        }

    }
}