package m.derakhshan.refectory.feature_credit.domain.repository


import kotlinx.coroutines.flow.Flow
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_credit.domain.model.CreditModel

interface CreditRepository {

    suspend fun getUserInfo(): UserModel

    suspend fun updateUserCredit()

    fun getUserCredit(): Flow<CreditModel?>

}