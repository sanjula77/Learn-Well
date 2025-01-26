package com.example.learnwell.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.learnwell.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavItem.Home.route) {
        composable(NavItem.Home.route) { HomeScreen() }
       // composable(NavItem.Search.route) { SearchScreen() }
       // composable(NavItem.Settings.route) { SettingsScreen() }
    }
}