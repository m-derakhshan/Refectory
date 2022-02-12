package m.derakhshan.refectory.feature_authentication.data.repository

import m.derakhshan.refectory.core.domain.model.Request
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_authentication.domain.repository.AuthenticationRepository

class FakeAuthenticationRepositoryAndroidTest : AuthenticationRepository {

    private var shouldReturnNetworkError: Boolean = false
    private val fakeUserList = mutableListOf<UserModel>()
    private val fakeUserData = UserModel(
        id = "1",
        name = "Mohammad",
        surname = "Derakhshan",
        taxCode = "123456",
        phoneNumber = "0913",
        email = "Mohammad@derakhshan",
        photo = "http://google.com"
    )

    fun shouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun login(taxCode: String): Request<UserModel> {
        return if (shouldReturnNetworkError)
            Request.Error(message = "Network Error")
        else
            Request.Success(fakeUserData)
    }

    override suspend fun signUp(user: UserModel): Request<UserModel> {
        return if (shouldReturnNetworkError)
            Request.Error(message = "Network Error")
        else
            Request.Success(fakeUserData)
    }

    override suspend fun insertUserInfo(user: UserModel) {
        fakeUserList.add(user)
    }
}