package m.derakhshan.refectory.core.domain.model





data class UserModel(
    val name: String = "",
    val surname: String = "",
    val photo: String = "",
    val phone: String = "",
    val email: String = "",
    val taxCode: String = ""
) {
    fun isUserRegistered() = name.isNotBlank() && surname.isNotBlank()
}
