package m.derakhshan.refectory.feature_authentication.domain.use_cases

import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class StoreUserDataInDatabase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(user: UserModel) {
        repository.insertUserInfo(user)
    }
}