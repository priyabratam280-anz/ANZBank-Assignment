package com.example.anzbankassignment

import com.example.anzbankassignment.data.UserApi
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
                name = "Era Schmeler",
                company = "Christiansen - Reinger",
                username = "Percival.Wyman26",
                email = "Sean.Ruecker11@gmail.com",
                address = "7536 Carmel Hollow",
                zip = "40445-5414",
                state = "Florida",
                country = "Nigeria",
                phone = "(811) 313-7616 x631",
                photo = "https://json-server.dev/ai-profiles/50.png"
            )
        )

        // Directly return List<UserDto>
        whenever(api.getUsers()).thenReturn(mockResponse)

        val users = repository.fetchUsers()

        assertEquals(1, users.size)
        assertEquals("Era Schmeler", users.first().name)
        assertEquals("Nigeria", users.first().country)
    }

    @Test(expected = Exception::class)
    fun `fetchUsers throws exception when API fails`() = runTest {
        whenever(api.getUsers()).thenThrow(RuntimeException("Network error"))
        repository.fetchUsers()
    }
}
