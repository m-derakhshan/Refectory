package m.derakhshan.refectory.feature_authentication.domain.use_cases

import kotlinx.coroutines.runBlocking
import m.derakhshan.refectory.feature_authentication.data.repository.FakeAuthenticationRepository
import m.derakhshan.refectory.feature_authentication.domain.model.InvalidTaxCodeException
import m.derakhshan.refectory.feature_authentication.domain.repository.AuthenticationRepository
import org.junit.Before
import org.junit.Test
import  com.google.common.truth.Truth.assertThat
import junit.framework.Assert.fail


class LoginUseCaseTest {

    private lateinit var fakeAuthenticationRepository: AuthenticationRepository
    private lateinit var loginUseCase: LoginUseCase


    @Before
    fun setup() {
        fakeAuthenticationRepository = FakeAuthenticationRepository()
        loginUseCase = LoginUseCase(fakeAuthenticationRepository)
    }

    @Test
    fun `empty tax code, return exception`(): Unit = runBlocking {
        try {
            loginUseCase(taxCode = "")
            fail("empty tax code, should raise exception")
        }catch (e:InvalidTaxCodeException){
            assertThat(e).hasMessageThat().contains("Tax Code can't left blank.")
        }

    }

    @Test
    fun `wrong length tax code, return exception`(): Unit = runBlocking {
        try {
            loginUseCase("123456")
            fail("wrong length tax code, should raise exception")
        } catch (e: InvalidTaxCodeException) {
            assertThat(e).hasMessageThat().contains("Length of Tax Code is not correct.")
        }
    }

}