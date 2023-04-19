package com.imgur.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.imgur.base.extensions.MutableSafeLiveData
import com.imgur.core_api.viewmodel.ViewModelFactory
import com.imgur.favorites.entity.FavoriteEntity
import com.imgur.favorites.list.FavoriteItemAdapter
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
class FavoritesFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = mock<FavoritesViewModel>()

    @Test
    fun `submitting list to RecyclerView`() {

        val adapterMock = mock<FavoriteItemAdapter>()

        val factory: ViewModelFactory = mock()
        whenever(factory.create<FavoritesViewModel>(any())).thenReturn(viewModel)

        val favoritesResult = MutableSafeLiveData<List<FavoriteEntity>>(emptyList())
        whenever(viewModel.favoriteList).thenReturn(favoritesResult)

        val fragmentScenario =
            launchFragmentInContainer<FavoritesFragment>(initialState = Lifecycle.State.CREATED)

        fragmentScenario.onFragment { fragment ->
            fragment.adapter = adapterMock
            fragment.viewModelFactory = factory
            fragmentScenario.moveToState(Lifecycle.State.STARTED)
        }

        viewModel.favoriteList.value = FavoritesStubs.favoritesList

        fragmentScenario.onFragment {
            assertEquals(FavoritesStubs.favoritesList.size, it.adapter.itemCount)
        }
    }
}