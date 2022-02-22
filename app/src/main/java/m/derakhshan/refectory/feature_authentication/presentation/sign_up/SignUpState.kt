package m.derakhshan.refectory.feature_authentication.presentation.sign_up

import android.net.Uri

data class SignUpState(
    val id: String = "",
    val taxCode: String = "",
    val name: String = "",
    val surname: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val photo: Uri? = null,
    val isSignUpExpanded: Boolean = true,
    val openImagePicker: Boolean = false
)


data class SignUpSnackBarState(
    val message: String = ""
)


data class SignUpNavigationState(
    val navigateToHomeScreen: Boolean = false
)
