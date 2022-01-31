package m.derakhshan.refectory.feature_authentication.data.data_source.dto

import androidx.annotation.Keep
import m.derakhshan.refectory.core.domain.model.UserModel

@Keep
data class UserServerModel(
    val name: String = "",
    val surname: String = "",
    val photo: String = "",
    val phone: String = "",
    val email: String = "",
    val taxCode: String = ""
)

fun UserServerModel.toUserModel(): UserModel {
    return UserModel(
        name = this.name,
        surname = this.surname,
        photo = this.photo,
        phone = this.phone,
        email = this.email,
        taxCode = this.taxCode
    )
}
