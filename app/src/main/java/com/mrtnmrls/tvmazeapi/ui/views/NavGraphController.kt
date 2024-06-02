package com.mrtnmrls.tvmazeapi.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mrtnmrls.tvmazeapi.ui.states.UiState
import com.mrtnmrls.tvmazeapi.ui.actions.ScheduleListAction
import com.mrtnmrls.tvmazeapi.ui.screens.ScheduleListScreen
import com.mrtnmrls.tvmazeapi.ui.screens.ShowDetailsScreen

@Composable
fun GraphNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    uiState: UiState,
    onAction: (ScheduleListAction) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = "scheduleListString"
    ) {
        composable("scheduleListString") {
            ScheduleListScreen(uiState = uiState) {
                onAction(it)
            }
        }
        composable("showDetailsScreen/{showId}") { navBackStackEntry ->
            val showId = navBackStackEntry.arguments?.getString("showId")
            showId?.let { id ->
                ShowDetailsScreen(tvShowResponse = uiState.tvShowState)
            }
        }
    }

    LaunchedEffect(uiState.showIdSelected) {
        if (uiState.showIdSelected != null) {
            navHostController.navigate("showDetailsScreen/${uiState.showIdSelected}")
        }
    }
}