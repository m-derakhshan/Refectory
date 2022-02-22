package m.derakhshan.refectory.feature_authentication.presentation.sign_up

import android.net.Uri

sealed class SignUpEvent {
    data class NameChanged(val name: String) : SignUpEvent()
    data class SurnameChanged(val surname: String) : SignUpEvent()
    data class PhoneChanged(val phone: String) : SignUpEvent()
    data class EmailChanged(val email: String) : SignUpEvent()
    data class PhotoChanged(val photo: Uri) : SignUpEvent()
    object AddImageClicked : SignUpEvent()
    object CloseImagePicker : SignUpEvent()
    object SignUp : SignUpEvent()
}
