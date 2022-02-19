package m.derakhshan.refectory.feature_credit.domain.use_cases


import m.derakhshan.refectory.feature_credit.domain.repository.CreditRepository
import javax.inject.Inject

class UpdateCreditsUseCase @Inject constructor(
    private val repository: CreditRepository
) {

    suspend operator fun invoke() {
        repository.updateUserCredit()
    }

}