package com.example.urbandictionarynike.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.urbandictionarynike.model.Definition

@Database(entities = [Definition::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UrbanDb : RoomDatabase() {

    abstract fun definitionDao(): DefinitionDao

    companion object {

        @Volatile
        private var INSTANCE: UrbanDb? = null

        fun getDatabase(context: Context): UrbanDb? {
            if (INSTANCE == null) {
                synchronized(UrbanDb::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            UrbanDb::class.java, "urban_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}