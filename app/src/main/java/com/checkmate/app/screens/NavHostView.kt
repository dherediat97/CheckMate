package com.checkmate.app.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.checkmate.app.config.NavigationItem


@Composable
fun NavHostView(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route,
    ) {
        composable(NavigationItem.Home.route) {
            TicketScreen()
        }

        composable(NavigationItem.TicketScreen.route) {
            TicketScreen()
        }
    }
}