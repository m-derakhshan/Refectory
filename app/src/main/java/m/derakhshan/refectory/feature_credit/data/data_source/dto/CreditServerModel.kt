package m.derakhshan.refectory.feature_credit.data.data_source.dto

import com.google.gson.annotations.SerializedName
import m.derakhshan.refectory.feature_credit.domain.model.CreditModel
import m.derakhshan.refectory.feature_credit.domain.model.Detail

data class CreditServerModel(
    @SerializedName("user_id")
    val userID: String,
    val details: List<Detail>,
    @SerializedName("total_credit")
    val totalCredit: Float
)

fun CreditServerModel.toCreditModel(): CreditModel {
    return CreditModel(
        userID = this.userID,
        details = this.details,
        totalCredit = this.totalCredit
    )
}
