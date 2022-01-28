package m.derakhshan.refectory.core.presentation

sealed class MainNavGraph(val route: String) {

    object AuthenticationScreen:MainNavGraph("AuthenticationScreen")
    object HomeScreen:MainNavGraph("HomeScreen")

}