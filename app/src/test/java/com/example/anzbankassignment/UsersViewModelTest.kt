package com.example.anzbankassignment

import com.example.anzbankassignment.data.User
import com.example.anzbankassignment.domain.UserRepository
import com.example.anzbankassignment.presentation.users.viewmodel.UsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UsersViewModelTest {

    private lateinit var repository: UserRepository
    private lateinit var viewModel: UsersViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = mock()
        viewModel = UsersViewModel(repository)
    }

    @Test
    fun `loadUsers updates uiState with users on success`() = runTest {
        val mockUsers = listOf(
            User(
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
        )

        whenever(repository.fetchUsers()).thenReturn(mockUsers)

        viewModel.loadUsers()
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(1, state.users.size)
        assertEquals("Sonia Zulauf", state.users.first().name)
        assertNull(state.error)
    }

    @Test
    fun `loadUsers updates uiState with error on failure`() = runTest {
        whenever(repository.fetchUsers()).thenThrow(RuntimeException("Network Interruption"))

        viewModel.loadUsers()
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertTrue(state.error!!.contains("Network Interruption"))
    }
}
