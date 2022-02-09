package m.derakhshan.refectory.feature_authentication.domain.use_cases

import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.fail
import kotlinx.coroutines.runBlocking
import m.derakhshan.refectory.feature_authentication.data.repository.FakeAuthenticationRepository
import m.derakhshan.refectory.feature_authentication.domain.model.InvalidUserDataException
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import org.junit.Before
import org.junit.Test


class SignUpUseCaseTest {

    private lateinit var fakeAuthenticationRepository: FakeAuthenticationRepository
    private lateinit var signUpUseCase: SignUpUseCase

    @Before
    fun setup() {
        fakeAuthenticationRepository = FakeAuthenticationRepository()
        signUpUseCase = SignUpUseCase(repository = fakeAuthenticationRepository)
    }

    @Test
    fun `empty name, raise Exception`(): Unit = runBlocking {
        try {
            signUpUseCase(
                UserModel(
                    id = "1",
                    name = "",
                    surname = "Derakhshan",
                    taxCode = "",
                    phoneNumber = "",
                    email = "",
                    photo = ""
                )
            )
            fail("empty name should raise InvalidUserDataException")
        } catch (e: InvalidUserDataException) {
            assertThat(e).hasMessageThat().contains("Name can't left blank")
        }
    }

    @Test
    fun `empty surname, raise Exception`(): Unit = runBlocking {
        try {
            signUpUseCase(
                UserModel(
                    id = "1",
                    name = "Mohammad",
                    surname = "",
                    taxCode = "",
                    phoneNumber = "",
                    email = "",
                    photo = ""
                )
            )
            fail("empty surname should raise InvalidUserDataException")
        } catch (e: InvalidUserDataException) {
            assertThat(e).hasMessageThat().contains("Surname can't left blank")
        }
    }
}