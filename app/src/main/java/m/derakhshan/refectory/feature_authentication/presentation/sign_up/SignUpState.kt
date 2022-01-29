package m.derakhshan.refectory.feature_authentication.presentation.sign_up

import android.net.Uri

data class SignUpState(
    val name: String = "",
    val surname: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val photo: Uri? = null,
    val isSignUpExpanded: Boolean = true
)
