package m.derakhshan.refectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import m.derakhshan.refectory.core.data.data_source.Setting
import m.derakhshan.refectory.feature_authentication.presentation.AuthenticationNavGraph
import m.derakhshan.refectory.feature_authentication.presentation.authenticationNavigation


import m.derakhshan.refectory.ui.theme.RefectoryTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var setting: Setting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startDestination = if (setting.isUserLoggedIn)
            AuthenticationNavGraph.Route.route
        else
        // TODO: change below line to home screen
            AuthenticationNavGraph.Route.route


        setContent {
            RefectoryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        authenticationNavigation(navController = navController)
                    }
                }
            }
        }
    }
}
