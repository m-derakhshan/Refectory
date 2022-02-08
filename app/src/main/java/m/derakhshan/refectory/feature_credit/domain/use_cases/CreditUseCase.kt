package m.derakhshan.refectory.feature_credit.domain.use_cases

data class CreditUseCase(
    val getUserProfileUseCase: GetUserProfileUseCase,
    val updateCreditsUseCase: UpdateCreditsUseCase,
    val getUserCreditUseCase: GetUserCreditUseCase
)