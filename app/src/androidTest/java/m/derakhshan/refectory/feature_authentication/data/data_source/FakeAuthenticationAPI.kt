package m.derakhshan.refectory.feature_authentication.data.data_source

import android.accounts.NetworkErrorException
import m.derakhshan.refectory.feature_authentication.data.data_source.dto.UserServerModel
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class FakeAuthenticationAPIAndroidTest @Inject constructor() : AuthenticationAPI {
    private var shouldReturnNetworkError = false
    private lateinit var errorType: NetworkErrorType
    private val fakeUserResponse = UserServerModel(
        id = "1",
        name = "Mohammad",
        surname = "Derakhshan",
        photo = "http://",
        phone = "0913",
        email = "Mohammad@derakhshan",
        taxCode = ""
    )

    fun shouldReturnNetworkError(value: Boolean, type: NetworkErrorType) {
        shouldReturnNetworkError = value
        errorType = type
    }

    sealed class NetworkErrorType {
        object NetworkErrorException : NetworkErrorType()
        object TimeoutException : NetworkErrorType()
        object UnknownHostException : NetworkErrorType()
        object Other : NetworkErrorType()
    }

    override suspend fun login(taxCode: String): UserServerModel {
        if (shouldReturnNetworkError) {
            throw(
                    when (errorType) {
                        is NetworkErrorType.NetworkErrorException -> NetworkErrorException()
                        is NetworkErrorType.UnknownHostException -> UnknownHostException()
                        is NetworkErrorType.TimeoutException -> TimeoutException()
                        is NetworkErrorType.Other -> Exception("Unknown error.")
                    })
        }
        return fakeUserResponse.copy(
            taxCode = taxCode
        )
    }

    override suspend fun signUp(user: UserModel): UserServerModel {
        if (shouldReturnNetworkError) {
            throw(
                    when (errorType) {
                        is NetworkErrorType.NetworkErrorException -> NetworkErrorException()
                        is NetworkErrorType.UnknownHostException -> UnknownHostException()
                        is NetworkErrorType.TimeoutException -> TimeoutException()
                        is NetworkErrorType.Other -> Exception("Unknown error.")
                    })
        }
        return fakeUserResponse.copy(
            taxCode = user.taxCode
        )
    }
}