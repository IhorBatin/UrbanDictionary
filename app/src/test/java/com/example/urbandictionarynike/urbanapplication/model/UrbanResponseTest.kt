package com.example.urbandictionarynike.urbanapplication.model

import com.example.urbandictionarynike.model.Definition
import com.example.urbandictionarynike.model.UrbanResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UrbanResponseTest {

    @Test
    fun urbanResponseConstructorTest() {
        val obj = UrbanResponse(list = listOf())
        assertEquals(listOf<Definition>(), obj.list)
    }
}