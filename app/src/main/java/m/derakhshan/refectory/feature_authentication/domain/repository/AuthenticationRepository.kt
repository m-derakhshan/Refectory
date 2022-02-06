package m.derakhshan.refectory.feature_authentication.domain.repository

import m.derakhshan.refectory.core.domain.model.Request
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel

interface AuthenticationRepository {

    suspend fun login(taxCode: String): Request<UserModel>

    suspend fun signUp(user: UserModel): Request<UserModel>

    suspend fun insertUserInfo(user: UserModel)
}