package com.example.anzbankassignment.presentation.users.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anzbankassignment.domain.User
import com.example.anzbankassignment.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repo: UserRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UserDetailUiState())
    val uiState: StateFlow<UserDetailUiState> = _uiState.asStateFlow()

    fun loadUser(id: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, error = null) }
        try {
            val user = repo.getUserById(id)
            _uiState.update { it.copy(isLoading = false, user = user) }
        } catch (e: Exception) {
            _uiState.update { it.copy(isLoading = false, error = e.message ?: "Unknown") }
        }
    }
}

data class UserDetailUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)
