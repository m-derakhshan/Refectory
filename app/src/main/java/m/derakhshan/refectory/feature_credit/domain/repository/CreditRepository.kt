package m.derakhshan.refectory.feature_credit.domain.repository


import m.derakhshan.refectory.feature_authentication.domain.model.UserModel

interface CreditRepository {

    suspend fun getUserInfo(): UserModel

}