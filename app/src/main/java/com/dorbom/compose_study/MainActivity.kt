package com.dorbom.compose_study

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dorbom.compose_study.chat.ChatScreen
import com.dorbom.compose_study.chat.ChatViewModel
import com.dorbom.compose_study.home.HomeScreen
import com.dorbom.compose_study.profile.ProfileScreen
import com.dorbom.compose_study.profile.ProfileScreenViewModel
import com.dorbom.compose_study.ui.theme.Compose_studyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val chatViewModel = ChatViewModel()
    val profileViewModel = ProfileScreenViewModel()

    Compose_studyTheme {
        Column {

            NavHost(
                modifier = Modifier
                    .weight(1f),
                navController = navController,
                startDestination = "profile") {

                composable(route = "home") {
                    HomeScreen()
                }
                composable(route = "chat") {
                    ChatScreen(navController, chatViewModel)
                }
                composable(route = "profile") {
                    ProfileScreen(navController = navController, viewModel = profileViewModel)
                }
            }

            Row {
                Button(
                    onClick = {
                        navController.navigate("home") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Black))
                {
                    Text(text = "HOME")
                }
                Button(
                    onClick = {
                        navController.navigate("chat") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Black))
                {
                    Text(text = "CHAT")
                }
                Button(
                    onClick = {
                        navController.navigate("profile") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Black))
                {
                    Text(text = "PROFILE")
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewMainScreen() {
    MainScreen()
}