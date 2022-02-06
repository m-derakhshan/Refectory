package m.derakhshan.refectory.feature_authentication.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val surname: String,

    @SerializedName("tax_code")
    val taxCode: String,

    @SerializedName("phone_number")
    val phoneNumber: String? = null,

    val email: String? = null,
    val photo: String? = null
) {
    fun isUserRegistered() = name.isNotBlank() && surname.isNotBlank()
}
