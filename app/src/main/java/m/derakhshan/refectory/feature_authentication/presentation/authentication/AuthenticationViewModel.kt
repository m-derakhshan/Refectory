package m.derakhshan.refectory.feature_authentication.presentation.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    //private val useCase: LoginUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AuthenticationState())
    val state: State<AuthenticationState> = _state

    fun onEvent(event: AuthenticationEvent) {
        when (event) {
            is AuthenticationEvent.TaxCodeChanged -> {
                _state.value = _state.value.copy(
                    taxCode = if (event.taxCode.length > 16) _state.value.taxCode else event.taxCode
                )
            }
            is AuthenticationEvent.Login -> {}
        }
    }
}