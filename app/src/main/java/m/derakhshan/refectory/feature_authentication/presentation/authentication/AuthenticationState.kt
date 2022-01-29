package m.derakhshan.refectory.feature_authentication.presentation.authentication

data class AuthenticationState(
    val taxCode: String = "",
    val version: String = "1.0.0",
    val isLoginExpanded: Boolean = true
)


data class AuthenticationNavigateState(
    val navigateToHomeScreen: Boolean = false,
    val navigateToSignUpScreen: Boolean = false
)