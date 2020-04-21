package com.example.urbandictionarynike.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.urbandictionarynike.model.Definition
import com.example.urbandictionarynike.repository.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UrbanViewModel(application: Application) : AndroidViewModel(application) {

    private val _definitionListResponse = MutableLiveData<List<Definition>>()
    val definitionListResponse: LiveData<List<Definition>>
        get() = _definitionListResponse

    private val repo = Repository(application)

    /**
     * loads previously saved definitions if any
     */
    fun loadSavedDefinitions() {
        viewModelScope.launch {
            repo.getSavedDefinitions()?.collect {
                _definitionListResponse.postValue(it)
            }
        }
    }

    /**
     * fetches list of definitions based on query provided
     * @param term is the query to be defined
     */
    fun queryDefinition(term: String) {
        viewModelScope.launch {
            repo.searchTerm(term).let { urbanResponse ->
                urbanResponse.list?.let { definitions -> repo.saveDefinitions(definitions) }
                _definitionListResponse.postValue(urbanResponse.list ?: listOf())
            }
        }
    }

    /**
     * Compares length of previous query string to current to determine if backspace action was performed
     *
     * @param lastTerm Previous query string
     * @param term Current query string
     * @return TRUE if it was last actioned performed to query string was backspace
     */
    fun isBackSpace(lastTerm: String, term: String): Boolean = lastTerm.length != term.length - 1
}