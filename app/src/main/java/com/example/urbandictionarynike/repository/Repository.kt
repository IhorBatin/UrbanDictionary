package com.example.urbandictionarynike.repository

import android.app.Application
import com.example.urbandictionarynike.model.Definition
import com.example.urbandictionarynike.repository.local.DefinitionDao
import com.example.urbandictionarynike.repository.local.UrbanDb
import com.example.urbandictionarynike.repository.remote.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class Repository(application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var definitionDao: DefinitionDao?

    init {
        val db = UrbanDb.getDatabase(application)
        definitionDao = db?.definitionDao()
    }

    suspend fun searchTerm(term: String) = RetrofitInstance.urbanService.getDefinitions(term)

    fun saveDefinitions(definitions: List<Definition>) {
        launch { saveDefinitionsBG(definitions) }
    }

    fun getSavedDefinitions() = definitionDao?.getDefinitions()

    private suspend fun saveDefinitionsBG(definitions: List<Definition>) {
        withContext(Dispatchers.IO) {
            definitionDao?.addNewDefinition(definitions)
        }
    }
}