package m.derakhshan.refectory.feature_authentication.domain.use_cases

import m.derakhshan.refectory.core.domain.model.Request
import m.derakhshan.refectory.feature_authentication.domain.model.InvalidUserDataException
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {

    @Throws
    suspend operator fun invoke(user: UserModel): Request<UserModel> {
        if (user.name.isBlank())
            throw InvalidUserDataException("Name can't left blank")
        if (user.surname.isBlank())
            throw InvalidUserDataException("Surname can't left blank")
        return repository.signUp(user = user)
    }
}