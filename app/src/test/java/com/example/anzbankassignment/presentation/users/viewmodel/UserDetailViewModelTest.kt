package com.example.anzbankassignment.presentation.users.viewmodel

import com.example.anzbankassignment.domain.User
import com.example.anzbankassignment.domain.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UserDetailViewModelTest {

    private lateinit var repository: UserRepository
    private lateinit var viewModel: UserDetailViewModel
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = mock()
        viewModel = UserDetailViewModel(repository)
    }

    @Test
    fun `loadUserDetail updates uiState with correct user`() = runTest {
        val mockUser = User(
            id = "1",
            name = "Sonia Zulauf",
            email = "Shaina69@yahoo.com",
            company = "Cartwright LLC",
            username = "Gisselle.Corwin88",
            address = "5270 New Road",
            zip = "01312-1994",
            state = "New York",
            country = "Zimbabwe",
            phone = "(944) 831-2523",
            photo = "https://json-server.dev/ai-profiles/77.png"
        )

        whenever(repository.getUserById("1")).thenReturn(mockUser)

        viewModel.loadUser("1")
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertNull(state.error)
        Assert.assertEquals("Sonia Zulauf", state.user?.name)
    }

    @Test
    fun `loadUserDetail updates uiState with null when user not found`() = runTest {
        // Repository returns null if user is not found
        whenever(repository.getUserById("100")).thenReturn(null)

        viewModel.loadUser("100")
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertNull(state.user)
        Assert.assertNull(state.error) // Since ViewModel doesn't set error for null user
    }

    @Test
    fun `loadUserDetail updates uiState with error when repository throws`() = runTest {
        whenever(repository.getUserById("1")).thenThrow(RuntimeException("Network Interruption"))

        viewModel.loadUser("1")
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertNull(state.user)
        Assert.assertEquals("Network Interruption", state.error)
    }
}
