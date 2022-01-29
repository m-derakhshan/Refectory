package m.derakhshan.refectory.feature_authentication.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import m.derakhshan.refectory.feature_authentication.presentation.authentication.composable.AuthenticationScreen
import m.derakhshan.refectory.feature_authentication.presentation.sign_up.composable.SignUpScreen


fun NavGraphBuilder.authenticationNavigation(
    navController: NavController
) {
    navigation(
        startDestination = AuthenticationNavGraph.AuthenticationScreen.route,
        route = AuthenticationNavGraph.Route.route
    ) {
        composable(AuthenticationNavGraph.AuthenticationScreen.route) {
            AuthenticationScreen(navController = navController)
        }
        composable(AuthenticationNavGraph.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
    }
}


sealed class AuthenticationNavGraph(val route: String) {
    object AuthenticationScreen : AuthenticationNavGraph("AuthenticationScreen")
    object SignUpScreen : AuthenticationNavGraph("SignUpScreen")
    object Route : AuthenticationNavGraph("AuthenticationRoute")
}