package m.derakhshan.refectory.feature_authentication.domain.use_cases

import android.content.Context
import m.derakhshan.refectory.R
import m.derakhshan.refectory.core.domain.model.Request
import m.derakhshan.refectory.feature_authentication.domain.model.InvalidUserDataException
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val context: Context,
    private val repository: AuthenticationRepository
) {

    @Throws
    suspend operator fun invoke(user: UserModel): Request<UserModel> {
        if (user.name.isBlank())
            throw InvalidUserDataException(context.getString(R.string.sign_up_error_empty_name))
        if (user.surname.isBlank())
            throw InvalidUserDataException(context.getString(R.string.sign_up_error_empty_surname))
        return repository.signUp(user = user)
    }
}