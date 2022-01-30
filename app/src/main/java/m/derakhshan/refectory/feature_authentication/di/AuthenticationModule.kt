package m.derakhshan.refectory.feature_authentication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.derakhshan.refectory.feature_authentication.domain.use_cases.AuthenticationUseCase
import m.derakhshan.refectory.feature_authentication.domain.use_cases.LoginUseCase
import m.derakhshan.refectory.feature_authentication.domain.use_cases.SignUpUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AuthenticationModule {

    @Singleton
    @Provides
    fun provideAuthenticationUseCase(): AuthenticationUseCase {
        return AuthenticationUseCase(
            loginUseCase = LoginUseCase(),
            signUpUseCase = SignUpUseCase()
        )
    }
}