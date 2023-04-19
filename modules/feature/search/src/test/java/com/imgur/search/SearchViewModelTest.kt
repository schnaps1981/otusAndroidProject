package com.imgur.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imgur.core_api.tools.SnackBarProducer
import com.imgur.network_api.extension.Response
import com.imgur.search.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: SearchRepository = mock()
    private val snackBarProducer: SnackBarProducer = mock()

    private val ioDispatcher: CoroutineDispatcher = StandardTestDispatcher()

    private val searchViewModel = SearchViewModel(
        repository,
        snackBarProducer,
        ioDispatcher
    )

    private val pageNum = 0
    private val query = "query"

    @Before
    fun before() {
        Dispatchers.setMain(ioDispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun testVM () {
        runTest {
            searchViewModel.searchText.value = "1234"
            searchViewModel.refreshAndSearch()
        }
    }

//    @Test
//    fun `success searchResult`() {
//        runTest {
//            whenever(repository.searchByQuery(pageNum, query)).thenReturn(
//                Response.Success(
//                    SearchStubs.successSearchByQueryItem
//                )
//            )
//
//            searchViewModel.refreshAndSearch()
//            val actual = searchViewModel.searchResult.value
//
//            Timber.d("")
//
//        }
//    }
}
