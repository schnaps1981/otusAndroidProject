package com.imgur.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imgur.core_api.tools.SnackBarProducer
import com.imgur.favorites.entity.FavoriteEntity
import com.imgur.favorites.repository.FavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
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


class FavoritesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: FavoriteRepository = mock()
    private val snackBarProducer: SnackBarProducer = mock()

    private val dispatcher: CoroutineDispatcher = StandardTestDispatcher()

    private val favoritesViewModel = FavoritesViewModel(
        repository,
        snackBarProducer,
        dispatcher,
        dispatcher
    )

    @Before
    fun before() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun getFavoritesSuccessTest() {
        runTest {
            val expected: List<FavoriteEntity> = FavoritesStubs.favoritesList

            whenever(repository.getFavorites()).thenReturn(expected)

            val actual = favoritesViewModel.favoriteList
            favoritesViewModel.onRefresh()

            assertEquals(expected, actual.value)
        }
    }
}