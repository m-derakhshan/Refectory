package m.derakhshan.refectory.feature_credit.domain.use_cases

import kotlinx.coroutines.flow.Flow
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_credit.domain.repository.CreditRepository

class GetUserProfileUseCase(
    private val repository: CreditRepository
) {
    suspend operator fun invoke(): UserModel {
        return repository.getUserInfo()
    }
}