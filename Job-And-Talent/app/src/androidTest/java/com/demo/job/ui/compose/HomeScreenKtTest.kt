package com.demo.job.ui.compose

import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.demo.job.MainActivity
import com.demo.job.ui.theme.DemoTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testLazyColumn() {
        composeTestRule.setContent {
            DemoTheme {
                HomeScreen(rememberNavController())
            }
        }
        val list = composeTestRule.onNode(hasTestTag("demo-list-testTag"), useUnmergedTree = true)
        list.assertIsNotDisplayed()
    }
}