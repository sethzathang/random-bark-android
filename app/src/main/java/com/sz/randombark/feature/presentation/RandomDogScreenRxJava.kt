package com.sz.randombark.feature.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sz.randombark.common.ViewState

@Composable
fun RandomDogScreenRxJava(
    viewModel: RandomBarkViewModel
) {
    viewModel.viewStateRxJava.observeAsState().value?.let { state ->
        when (state) {
            is ViewState.Loading -> {
                CircularProgressIndicator()
            }

            is ViewState.Error -> {
                Text(text = state.message)
            }

            is ViewState.Success -> {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = state.data.breed
                )
                AsyncImage(
                    model = state.data.image,
                    contentDescription = "random dog image"
                )
                Button(
                    modifier = Modifier.padding(vertical = 16.dp),
                    onClick = { viewModel.fetchRandomDogRxJava() },
                    content = { Text(text = "Fetch") }
                )
            }
        }
    }
}