package m.derakhshan.refectory.feature_authentication.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    init {
        _state.value = _state.value.copy(
            taxCode = savedStateHandle.get<String>("tax_code") ?: _state.value.taxCode
        )
    }

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.SignUp -> {
                _state.value = _state.value.copy(
                    isSignUpExpanded = false
                )
                // TODO: add sign up logic
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

}