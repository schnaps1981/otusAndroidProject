package com.imgur

import android.view.View
import com.imgur.search.SearchFragment
import com.imgur.search.list.SearchItemAdapter
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object SearchScreen : KScreen<SearchScreen>() {
    override val layoutId: Int = com.imgur.search.R.layout.fmt_search
    override val viewClass: Class<*> = SearchFragment::class.java

    val searchEditTextFiled = KEditText { withId(com.imgur.search.R.id.searchEditTextField) }

    val searchNothingView = KTextView { withId(com.imgur.search.R.id.searchNothingText) }

    val searchResultList =
        KRecyclerView(
            builder = { withId(com.imgur.search.R.id.searchItemsList) },
            itemTypeBuilder = { itemType(SearchScreen::ListItem) }
        )

    class ListItem(parent: Matcher<View>) : KRecyclerItem<SearchItemAdapter>(parent)
}
