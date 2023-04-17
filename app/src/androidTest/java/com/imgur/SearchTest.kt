package com.imgur

import androidx.test.ext.junit.rules.activityScenarioRule
import com.imgur.core_api.Constants
import com.imgur.main.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class SearchTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun defaultStateSearchScreenTest() = run {
        step("just start application") {
            SearchScreen {
                searchNothingView.isVisible()
            }
        }
    }

    @Test
    fun searchByKeywordTest() =
        before {
            SearchScreen.searchEditTextFiled.clearText()
        }.after {
            SearchScreen.searchEditTextFiled.clearText()
        }.run {
            step("fill search request $KEYWORD to edit text") {
                SearchScreen.searchEditTextFiled.typeText(KEYWORD)
            }

            step("wait results in recycler view") {
                flakySafely(
                    timeoutMs = Constants.okHttpConnectionTimeOut * 1000L,
                    intervalMs = 100
                ) {
                    SearchScreen.searchResultList {
                        assert(getSize() > 0)
                    }
                }
            }
        }

    companion object {
        private const val KEYWORD = "dogs"
    }
}
