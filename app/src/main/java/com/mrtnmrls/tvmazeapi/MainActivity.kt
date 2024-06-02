package com.mrtnmrls.tvmazeapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mrtnmrls.tvmazeapi.ui.screens.ScheduleListScreen
import com.mrtnmrls.tvmazeapi.ui.theme.TvMazeApiTheme
import com.mrtnmrls.tvmazeapi.ui.viewmodels.TvScheduleListViewModel
import com.mrtnmrls.tvmazeapi.ui.views.GraphNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val tvScheduleListViewModel = viewModel<TvScheduleListViewModel>()
            val uiState by tvScheduleListViewModel.uiState.collectAsState()
            val navController = rememberNavController()
            TvMazeApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    GraphNavHost(
                        modifier = Modifier.padding(paddingValues),
                        navHostController = navController,
                        uiState = uiState
                    ) { action ->
                        tvScheduleListViewModel.dispatchActions(action)
                    }
                }
            }
        }
    }
}
