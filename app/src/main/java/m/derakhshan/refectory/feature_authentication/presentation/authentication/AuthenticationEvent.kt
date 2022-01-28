package m.derakhshan.refectory.feature_authentication.presentation.authentication

sealed class AuthenticationEvent {
    data class TaxCodeChanged(val taxCode: String) : AuthenticationEvent()
    object Login : AuthenticationEvent()
}
