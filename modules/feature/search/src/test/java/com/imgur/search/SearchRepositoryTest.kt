package com.imgur.search

import com.imgur.database_api.FavoritesDao
import com.imgur.database_api.dto.FavoriteItem
import com.imgur.network_api.api.ImgurApi
import com.imgur.network_api.extension.Response
import com.imgur.search.repository.SearchRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.HttpException


class SearchRepositoryTest {

    private val accountApi: ImgurApi = mock()
    private val favoriteDao: FavoritesDao = mock()
    private val errorApiResponse: HttpException = mock()

    private val repo = SearchRepositoryImpl(accountApi, favoriteDao)

    private val pageNum = 0
    private val query = "query"

    private val favoriteItem = SearchStubs.favoriteItem

    @Test
    fun `success searchByQuery`() {
        runBlocking {
            whenever(
                accountApi.searchImages(
                    pageNum,
                    query
                )
            ).thenReturn(SearchStubs.getSuccessSearchByQuery())
            val expected = Response.Success(SearchStubs.successSearchByQueryItem)

            val actual = repo.searchByQuery(pageNum, query)


            assertEquals(expected, actual)
            verify(accountApi).searchImages(pageNum, query)
        }
    }

    @Test
    fun `error searchByQuery`() {
        runBlocking {
            whenever(
                accountApi.searchImages(
                    pageNum,
                    query
                )
            ).thenThrow(errorApiResponse)
            val expected = Response.Failure(false, 0, null)

            val actual = repo.searchByQuery(pageNum, query)

            assertEquals(expected, actual)
            verify(accountApi).searchImages(pageNum, query)
        }
    }

    @Test
    fun `add to favorite`() {
        runBlocking {
            whenever(favoriteDao.addFavorite(favoriteItem)).thenReturn(1)
            val expected = 1L

            val actual = repo.addToFavorite(favoriteItem)

            assertEquals(expected, actual)
            verify(favoriteDao).addFavorite(favoriteItem)
        }
    }
}
