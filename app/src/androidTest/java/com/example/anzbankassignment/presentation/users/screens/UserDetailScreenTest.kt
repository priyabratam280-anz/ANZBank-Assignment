package com.example.anzbankassignment.presentation.users.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.anzbankassignment.domain.User
import com.example.anzbankassignment.presentation.users.viewmodel.UserDetailUiState
import org.junit.Rule
import org.junit.Test

class UserDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingState_showsLoadingIndicator() {
        composeTestRule.setContent {
            UserDetailContent(state = UserDetailUiState(isLoading = true))
        }

        composeTestRule.onNodeWithTag("LoadingIndicator").assertIsDisplayed()
    }

    @Test
    fun successState_showsUserDetails() {
        val testUser = User(
            id = "1",
            name = "John Doe",
            email = "john@example.com",
            company = "Test Co",
            username = "johnd",
            address = "123 Street",
            zip = "12345",
            state = "Test State",
            country = "Test Country",
            phone = "123-456-7890",
            photo = "https://example.com/photo.jpg"
        )

        composeTestRule.setContent {
            UserDetailContent(state = UserDetailUiState(user = testUser))
        }

        composeTestRule.onNodeWithTag("UserDetailContent").assertIsDisplayed()
        composeTestRule.onNodeWithText("Name: John Doe").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email: john@example.com").assertIsDisplayed()
        composeTestRule.onNodeWithTag("UserDetailRow_Company").assertIsDisplayed()
    }

    @Test
    fun errorState_showsErrorMessage() {
        val errorMessage = "Network Error"
        composeTestRule.setContent {
            UserDetailContent(state = UserDetailUiState(error = errorMessage))
        }

        composeTestRule.onNodeWithTag("ErrorBox").assertIsDisplayed()
        composeTestRule.onNodeWithText("Error: $errorMessage").assertIsDisplayed()
    }
}
