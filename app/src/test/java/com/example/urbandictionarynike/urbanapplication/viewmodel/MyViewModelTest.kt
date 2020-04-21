package com.example.urbandictionarynike.urbanapplication.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.urbandictionarynike.model.Definition
import com.example.urbandictionarynike.model.UrbanResponse
import com.example.urbandictionarynike.repository.remote.RetrofitInstance
import com.example.urbandictionarynike.repository.remote.UrbanService
import com.example.urbandictionarynike.urbanapplication.utils.TestCoroutineRule
import com.example.urbandictionarynike.viewmodel.UrbanViewModel
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MyViewModelTest {
    @get:Rule
    var instantTestExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var testCoroutineRule = TestCoroutineRule()
    private lateinit var viewModel: UrbanViewModel
    private val app = mockk<Application>() {
        every { applicationContext } returns mockk()
    }

    private val urbanService = mockk<UrbanService> {
        coEvery { getDefinitions(any()) } returns UrbanResponse(listOf())
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = UrbanViewModel(app)
        mockkObject(RetrofitInstance)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun queryDefinitionTest() {
        testCoroutineRule.runBlockingTest {
            val mockObserver = spyk(Observer<List<Definition>> { })
            viewModel.definitionListResponse.observeForever(mockObserver)
            every { RetrofitInstance.urbanService } returns urbanService

            viewModel.queryDefinition("term")
            val captureList = mutableListOf<List<Definition>>()
            verify { mockObserver.onChanged(capture(captureList)) }

            val response = captureList[0]
            assertEquals(listOf<Definition>(), response)
        }
    }
}