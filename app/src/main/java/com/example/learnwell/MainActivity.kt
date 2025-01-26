package com.example.learnwell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.learnwell.ui.theme.LearnWellTheme
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.example.learnwell.Navigation.NavGraph
import com.example.learnwell.Navigation.NavItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnWellTheme {
                val navController = rememberNavController()

                val navItems = listOf(
                    NavItem.Home,
                    NavItem.Search,
                    NavItem.Settings
                )

                val currentBackStackEntry by navController.currentBackStackEntryFlow.collectAsStateWithLifecycle(null)
                val currentRoute = currentBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            navItems.forEach { navItem ->
                                val selected = currentRoute == navItem.route
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = {
                                        navController.navigate(navItem.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    label = { Text(text = navItem.title) },
                                    icon = {
                                        Icon(
                                            imageVector = if (selected) navItem.selectedIcon else navItem.unselectedIcon,
                                            contentDescription = navItem.title
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
