package m.derakhshan.refectory.feature_authentication.data.repository

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import m.derakhshan.refectory.feature_authentication.data.data_source.FakeAuthenticationAPI
import m.derakhshan.refectory.feature_authentication.data.data_source.FakeUserDao
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import org.junit.Before
import org.junit.Test

class AuthenticationRepositoryImplTest {

    private lateinit var authenticationRepositoryImpl: AuthenticationRepositoryImpl
    private lateinit var fakeUserDao: FakeUserDao
    private lateinit var fakeAuthenticationAPI: FakeAuthenticationAPI
    private val fakeUserInfo = UserModel(
        id = "1",
        name = "Mohammad",
        surname = "Derakhshan",
        photo = "http://",
        phoneNumber = "0913",
        email = "Mohammad@derakhshan",
        taxCode = "12345678910"
    )
    private val errors = mapOf(
        Pair(
            FakeAuthenticationAPI.NetworkErrorType.NetworkErrorException,
            "Network error occurred."
        ),
        Pair(
            FakeAuthenticationAPI.NetworkErrorType.TimeoutException,
            "Connection time out."
        ),
        Pair(
            FakeAuthenticationAPI.NetworkErrorType.UnknownHostException,
            "Can't connect to network."
        ),
        Pair(
            FakeAuthenticationAPI.NetworkErrorType.Other,
            "Unknown error."
        ),
    )

    @Before
    fun setup() {
        fakeAuthenticationAPI = FakeAuthenticationAPI()
        fakeUserDao = FakeUserDao()
        authenticationRepositoryImpl =
            AuthenticationRepositoryImpl(api = fakeAuthenticationAPI, userDao = fakeUserDao)
    }


    @Test
    fun `successfully inserting user data, true`(): Unit = runBlocking {
        authenticationRepositoryImpl.insertUserInfo(fakeUserInfo)
        assertThat(fakeUserInfo).isIn(fakeUserDao.fakeDatabase)
    }

    @Test
    fun ` Login Network Errors, returns RequestError with suitable response`(): Unit = runBlocking {
        for (error in errors) {
            fakeAuthenticationAPI.shouldReturnNetworkError(true, error.key)
            val response = authenticationRepositoryImpl.login("123456")
            assertThat(response.message).contains(error.value)
        }
    }

    @Test
    fun `Login successfully getting user data, returns true`(): Unit = runBlocking {
        val response = authenticationRepositoryImpl.login("123456")
        assertThat(response.data?.taxCode).contains("123456")
    }


    @Test
    fun ` SignUp Network Errors, returns RequestError with suitable response`(): Unit =
        runBlocking {
            for (error in errors) {
                fakeAuthenticationAPI.shouldReturnNetworkError(true, error.key)
                val response = authenticationRepositoryImpl.signUp(user = fakeUserInfo)
                assertThat(response.message).contains(error.value)
            }
        }


    @Test
    fun `SignUp successfully getting user data, returns true`(): Unit = runBlocking {
        val response = authenticationRepositoryImpl.signUp(user = fakeUserInfo)
        assertThat(response.data?.taxCode).contains(fakeUserInfo.taxCode)
    }
}