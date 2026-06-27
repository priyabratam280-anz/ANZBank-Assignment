package com.example.anzbankassignment.presentation.users.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.anzbankassignment.presentation.users.viewmodel.UserDetailUiState
import com.example.anzbankassignment.presentation.users.viewmodel.UserDetailViewModel

@Composable
fun UserDetailScreen(
    userId: String,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(userId) { viewModel.loadUser(userId) }

    UserDetailContent(state = state)
}

@Composable
fun UserDetailContent(state: UserDetailUiState) {
    when {
        state.isLoading -> Box(
            modifier = Modifier
                .fillMaxSize()
                .testTag("LoadingIndicator"),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        state.user != null -> {
            val user = state.user
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .testTag("UserDetailContent"),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(user.photo),
                    contentDescription = "User Photo",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(8.dp)
                        .border(
                            width = 4.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
                UserDetailRow(text = "Name", value = user.name)
                UserDetailRow(text = "Email", value = user.email)
                UserDetailRow(text = "Company", value = user.company)
                UserDetailRow(text = "Username", value = user.username)
                UserDetailRow(text = "Phone", value = user.phone)
                UserDetailRow(
                    text = "Address",
                    value = "${user.address}, ${user.state}, ${user.country} - ${user.zip}",
                )
            }
        }

        state.error != null -> Box(
            modifier = Modifier
                .fillMaxSize()
                .testTag("ErrorBox"),
            contentAlignment = Alignment.Center
        ) {
            Text("Error: ${state.error}")
        }

        else -> Box(
            modifier = Modifier
                .fillMaxSize()
                .testTag("EmptyBox"),
            contentAlignment = Alignment.Center
        ) {
            Text("User not found")
        }
    }
}

@Composable
fun UserDetailRow(text: String, value: String?) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$text: ")
            }
            append(value ?: "N/A")
        },
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .testTag("UserDetailRow_$text")
    )
}