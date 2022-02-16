package m.derakhshan.refectory.feature_credit.presentation

import androidx.navigation.*
import androidx.navigation.compose.composable
import m.derakhshan.refectory.feature_credit.presentation.home.composable.HomeScreen
import m.derakhshan.refectory.feature_credit.presentation.restaurant.composable.RestaurantScreen


fun NavGraphBuilder.homeNavigation(
    navController: NavController
) {
    navigation(
        startDestination = HomeNavGraph.HomeScreen.route,
        route = HomeNavGraph.Route.route,
    ) {
        composable(HomeNavGraph.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(HomeNavGraph.RestaurantScreen.route + "restaurant_id={restaurant_id}",
            arguments = listOf(
                navArgument("restaurant_id") {
                    type = NavType.IntType
                }
            )) {
            RestaurantScreen(navController = navController)
        }
    }
}


sealed class HomeNavGraph(val route: String) {
    object HomeScreen : HomeNavGraph("HomeScreen")
    object RestaurantScreen : HomeNavGraph("RestaurantScreen")
    object Route : HomeNavGraph("HomeRoute")
}
