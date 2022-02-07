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
    val phoneNumber: String = "",

    val email: String = "",
    val photo: String = "",

    val credit: Float = 0f
) {
    fun isUserRegistered() = name.isNotBlank() && surname.isNotBlank()
}
