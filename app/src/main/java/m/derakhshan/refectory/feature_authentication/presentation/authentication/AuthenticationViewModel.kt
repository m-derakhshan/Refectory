package m.derakhshan.refectory.feature_authentication.presentation.authentication


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import m.derakhshan.refectory.core.domain.model.Request
import m.derakhshan.refectory.feature_authentication.domain.use_cases.AuthenticationUseCase
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val useCase: AuthenticationUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AuthenticationState())
    val state: State<AuthenticationState> = _state

    private val _snackBar = MutableSharedFlow<AuthenticationSnackbarState>()
    val snackBar = _snackBar.asSharedFlow()

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
            is AuthenticationEvent.Snackbar -> {
                viewModelScope.launch {
                    _snackBar.emit(
                        AuthenticationSnackbarState(message = event.message)
                    )
                }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            try {
                when (val result = useCase.loginUseCase(taxCode = _state.value.taxCode)) {
                    is Request.Success -> {
                        _navigate.emit(
                            AuthenticationNavigateState(
                                navigateToSignUpScreen = !result.data.isUserRegistered(),
                                navigateToHomeScreen = result.data.isUserRegistered()
                            )
                        )
                    }
                    is Request.Error -> {
                        _snackBar.emit(AuthenticationSnackbarState(result.message))
                    }
                }
            }
            catch (e: Exception) {
                _snackBar.emit(AuthenticationSnackbarState(e.message ?: "Unknown error."))
            }
            _state.value = _state.value.copy(
                isLoginExpanded = true
            )
        }
    }
}