package m.derakhshan.refectory.feature_authentication.domain.use_cases

data class AuthenticationUseCase(
    val loginUseCase: LoginUseCase,
    val signUpUseCase: SignUpUseCase
)
