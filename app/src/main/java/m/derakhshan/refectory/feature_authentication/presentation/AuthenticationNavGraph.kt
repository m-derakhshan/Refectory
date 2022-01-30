package m.derakhshan.refectory.feature_authentication.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import m.derakhshan.refectory.feature_authentication.presentation.authentication.composable.AuthenticationScreen
import m.derakhshan.refectory.feature_authentication.presentation.sign_up.composable.SignUpScreen


@ExperimentalMaterialApi
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
        composable(AuthenticationNavGraph.SignUpScreen.route + "/tax_code={tax_code}",
            arguments = listOf(
                navArgument(name = "tax_code") {
                    type = NavType.StringType
                }
            )) {
            SignUpScreen(navController = navController)
        }
    }
}


sealed class AuthenticationNavGraph(val route: String) {
    object AuthenticationScreen : AuthenticationNavGraph("AuthenticationScreen")
    object SignUpScreen : AuthenticationNavGraph("SignUpScreen")
    object Route : AuthenticationNavGraph("AuthenticationRoute")
}