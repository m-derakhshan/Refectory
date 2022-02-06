package m.derakhshan.refectory.feature_authentication.data.repository

import android.accounts.NetworkErrorException
import m.derakhshan.refectory.core.domain.model.Request
import m.derakhshan.refectory.feature_authentication.data.data_source.AuthenticationAPI
import m.derakhshan.refectory.feature_authentication.data.data_source.dao.UserDao
import m.derakhshan.refectory.feature_authentication.data.data_source.dto.toUserModel
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_authentication.domain.repository.AuthenticationRepository
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject


class AuthenticationRepositoryImpl @Inject constructor(
    private val api: AuthenticationAPI,
    private val userDao: UserDao
) : AuthenticationRepository {

    override suspend fun login(taxCode: String): Request<UserModel> {
        return try {
            Request.Success(data = api.login(taxCode).toUserModel())
        } catch (e: Exception) {
            Request.Error(
                message = when (e) {
                    is NetworkErrorException -> "Network error occurred."
                    is TimeoutException -> "Connection time out."
                    is UnknownHostException -> "Can't connect to network."
                    else -> e.message ?: "Unknown error."
                }
            )
        }
    }

    override suspend fun signUp(user: UserModel): Request<UserModel> {
        return try {
            Request.Success(data = api.signUp(user).toUserModel())
        } catch (e: Exception) {
            Request.Error(
                message = when (e) {
                    is NetworkErrorException -> "Network error occurred."
                    is TimeoutException -> "Connection time out."
                    is UnknownHostException -> "Can't connect to network."
                    else -> e.message ?: "Unknown error."
                }
            )
        }
    }

    override suspend fun insertUserInfo(user: UserModel) {
        userDao.insert(user)
    }
}