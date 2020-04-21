package com.example.urbandictionarynike.repository.local

import androidx.room.*
import com.example.urbandictionarynike.model.Definition
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DefinitionDao {

    @Query("SELECT * FROM definition")
    abstract fun getDefinitions(): Flow<List<Definition>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertAll(definitions: List<Definition>)

    @Query("DELETE FROM definition")
    abstract suspend fun delete()

    /**
     * Will first clear Database of definitions before adding new list
     *
     * @param definitions list of definitions to be added to Database
     */
    @Transaction
    open suspend fun addNewDefinition(definitions: List<Definition>) {
        delete()
        insertAll(definitions)
    }
}