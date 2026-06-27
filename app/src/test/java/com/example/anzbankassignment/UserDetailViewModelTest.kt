package com.example.anzbankassignment

import com.example.anzbankassignment.data.User
import com.example.anzbankassignment.domain.UserRepository
import com.example.anzbankassignment.presentation.users.viewmodel.UserDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class UserDetailViewModelTest {

    private lateinit var repository: UserRepository
    private lateinit var viewModel: UserDetailViewModel
    private val dispatcher = StandardTestDispatcher()

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
            name = "Era Schmeler",
            email = "Sean.Ruecker11@gmail.com",
            company = "Christiansen - Reinger",
            username = "Percival.Wyman26",
            address = "7536 Carmel Hollow",
            zip = "40445-5414",
            state = "Florida",
            country = "Nigeria",
            phone = "(811) 313-7616 x631",
            photo = "https://json-server.dev/ai-profiles/50.png"
        )

        whenever(repository.getUserById("1")).thenReturn(mockUser)

        viewModel.loadUser("1")
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertNull(state.error)
        assertEquals("Era Schmeler", state.user?.name)
    }

    @Test
    fun `loadUserDetail updates uiState with null when user not found`() = runTest {
        // Repository returns null if user is not found
        whenever(repository.getUserById("999")).thenReturn(null)

        viewModel.loadUser("999")
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertNull(state.user)
        assertNull(state.error) // Since ViewModel doesn't set error for null user
    }

    @Test
    fun `loadUserDetail updates uiState with error when repository throws`() = runTest {
        whenever(repository.getUserById("1")).thenThrow(RuntimeException("Network Down"))

        viewModel.loadUser("1")
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertNull(state.user)
        assertEquals("Network Down", state.error)
    }
}
