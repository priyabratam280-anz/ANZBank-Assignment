package com.example.anzbankassignment

import com.example.anzbankassignment.data.remote.UserApi
import com.example.anzbankassignment.data.UserDto
import com.example.anzbankassignment.data.UserRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UserRepositoryImplTest {

    private lateinit var api: UserApi
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setup() {
        api = mock()
        repository = UserRepositoryImpl(api)
    }

    @Test
    fun `fetchUsers returns mapped user list when API call succeeds`() = runTest {
        val mockResponse = listOf(
            UserDto(
                id = 1,
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

        // Directly return List<UserDto>
        whenever(api.getUsers()).thenReturn(mockResponse)

        val users = repository.fetchUsers()

        assertEquals(1, users.size)
        assertEquals("Sonia Zulauf", users.first().name)
        assertEquals("Zimbabwe", users.first().country)
    }

    @Test(expected = Exception::class)
    fun `fetchUsers throws exception when API fails`() = runTest {
        whenever(api.getUsers()).thenThrow(RuntimeException("Network Interruption"))
        repository.fetchUsers()
    }
}
