package m.derakhshan.refectory.feature_authentication.presentation.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(

) : ViewModel() {

    private val _state = mutableStateOf(AuthenticationState())
    val state: State<AuthenticationState> = _state

    private val _navigate = MutableSharedFlow<AuthenticationNavigateState>()
    val navigate = _navigate.asSharedFlow()

    fun onEvent(event: AuthenticationEvent) {
        when (event) {
            is AuthenticationEvent.TaxCodeChanged -> {
                _state.value = _state.value.copy(
                    taxCode = if (event.taxCode.length > 16) _state.value.taxCode else event.taxCode
                )
            }
            is AuthenticationEvent.Login -> {
                _state.value = _state.value.copy(
                    isLoginExpanded = false
                )
                login()
            }
        }
    }

    private fun login(){
        // TODO: implement login logic here
        viewModelScope.launch {
            delay(2000)
            _navigate.emit(
                AuthenticationNavigateState(
                    navigateToSignUpScreen = true
                )
            )
            _state.value = _state.value.copy(
                isLoginExpanded = true
            )
        }
    }
}