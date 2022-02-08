package m.derakhshan.refectory.feature_credit.domain.use_cases

import kotlinx.coroutines.flow.Flow
import m.derakhshan.refectory.feature_credit.domain.model.CreditModel
import m.derakhshan.refectory.feature_credit.domain.repository.CreditRepository
import javax.inject.Inject

class GetUserCreditUseCase @Inject constructor(
    private val repository: CreditRepository
) {

    operator fun invoke(): Flow<CreditModel> {
        return repository.getUserCredit()
    }

}