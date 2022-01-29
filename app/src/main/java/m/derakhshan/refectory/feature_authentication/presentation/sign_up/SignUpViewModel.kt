package m.derakhshan.refectory.feature_authentication.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

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
                    name = event.name
                )
            }
            is SignUpEvent.SurnameChanged -> {
                _state.value = _state.value.copy(
                    surname = event.surname
                )
            }
            is SignUpEvent.PhoneChanged -> {
                _state.value = _state.value.copy(
                    phoneNumber = event.phone
                )
            }
            is SignUpEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email
                )
            }
            is SignUpEvent.PhotoChanged -> {
                _state.value = _state.value.copy(
                    photo = event.photo
                )
            }
        }
    }
}