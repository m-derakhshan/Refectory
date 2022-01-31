package m.derakhshan.refectory.feature_authentication.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import m.derakhshan.refectory.core.domain.model.Request
import m.derakhshan.refectory.core.domain.model.UserModel
import m.derakhshan.refectory.feature_authentication.domain.use_cases.AuthenticationUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: AuthenticationUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    private val _snackBar = MutableSharedFlow<SignUpSnackBarState>()
    val snackBar = _snackBar.asSharedFlow()


    private val _navigate = MutableSharedFlow<SignUpNavigationState>()
    val navigate = _navigate.asSharedFlow()


    init {
        _state.value = _state.value.copy(
            taxCode = savedStateHandle.get<String>("tax_code") ?: _state.value.taxCode
        )
    }

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.SignUp -> {
                signUp()
            }
            is SignUpEvent.NameChanged -> {
                _state.value = _state.value.copy(
                    name = enterRemoval(event.name)
                )
            }
            is SignUpEvent.SurnameChanged -> {
                _state.value = _state.value.copy(
                    surname = enterRemoval(event.surname)
                )
            }
            is SignUpEvent.PhoneChanged -> {
                _state.value = _state.value.copy(
                    phoneNumber = enterRemoval(event.phone)
                )
            }
            is SignUpEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = enterRemoval(event.email)
                )
            }
            is SignUpEvent.PhotoChanged -> {
                _state.value = _state.value.copy(
                    photo = event.photo
                )
            }
        }
    }

    private fun enterRemoval(text: String) = text.replace("\n", "")

    private fun signUp() {
        _state.value = _state.value.copy(
            isSignUpExpanded = false
        )

        viewModelScope.launch {

            try {
                val result = useCase.signUpUseCase(
                    UserModel(
                        name = _state.value.name,
                        surname = _state.value.surname,
                        photo = _state.value.photo.toString(),
                        phone = _state.value.phoneNumber,
                        email = _state.value.email,
                        taxCode = _state.value.taxCode
                    )
                )
                when (result) {
                    is Request.Success -> {
                        _navigate.emit(
                            SignUpNavigationState(
                                navigateToHomeScreen = true
                            )
                        )
                    }
                    is Request.Error -> {
                        _snackBar.emit(
                            SignUpSnackBarState(message = result.message)
                        )
                    }
                }
            }catch (e:Exception){
                _snackBar.emit(
                    SignUpSnackBarState(message = e.message ?: "Unknown error.")
                )
            }

            _state.value = _state.value.copy(
                isSignUpExpanded = true
            )
        }
    }
}