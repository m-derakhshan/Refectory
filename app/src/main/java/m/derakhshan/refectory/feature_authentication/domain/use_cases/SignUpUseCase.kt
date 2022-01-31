package m.derakhshan.refectory.feature_authentication.domain.use_cases

import kotlinx.coroutines.delay
import m.derakhshan.refectory.core.domain.model.Request
import m.derakhshan.refectory.core.domain.model.UserModel

class SignUpUseCase {
    suspend operator fun invoke(user: UserModel): Request<UserModel> {
        delay(2000)
        // TODO: implement server request for signing up
        return Request.Success(
            data = user
        )
    }
}