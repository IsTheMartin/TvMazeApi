package com.mrtnmrls.tvmazeapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mrtnmrls.tvmazeapi.ui.screens.ScheduleListScreen
import com.mrtnmrls.tvmazeapi.ui.theme.TvMazeApiTheme
import com.mrtnmrls.tvmazeapi.ui.viewmodels.TvScheduleListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val tvScheduleListViewModel = viewModel<TvScheduleListViewModel>()
            val uiState by tvScheduleListViewModel.uiState.collectAsState()
            TvMazeApiTheme {
                ScheduleListScreen(scheduleResponse = uiState.resultState)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TvMazeApiTheme {
        Greeting("Android")
    }
}