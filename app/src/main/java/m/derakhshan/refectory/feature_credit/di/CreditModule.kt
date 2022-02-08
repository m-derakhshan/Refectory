package m.derakhshan.refectory.feature_credit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.derakhshan.refectory.core.data.data_source.MyDatabase
import m.derakhshan.refectory.core.domain.utils.Constants
import m.derakhshan.refectory.feature_credit.data.data_source.CreditAPI
import m.derakhshan.refectory.feature_credit.data.repository.CreditRepositoryImpl
import m.derakhshan.refectory.feature_credit.domain.repository.CreditRepository
import m.derakhshan.refectory.feature_credit.domain.use_cases.CreditUseCase
import m.derakhshan.refectory.feature_credit.domain.use_cases.GetUserCreditUseCase
import m.derakhshan.refectory.feature_credit.domain.use_cases.GetUserProfileUseCase
import m.derakhshan.refectory.feature_credit.domain.use_cases.UpdateCreditsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CreditModule {

    @Singleton
    @Provides
    fun provideCreditRepository(db: MyDatabase, api: CreditAPI): CreditRepository {
        return CreditRepositoryImpl(
            userDao = db.userDao,
            userCredit = db.creditDao,
            creditAPI = api
        )
    }

    @Singleton
    @Provides
    fun provideCreditUseCase(repository: CreditRepository): CreditUseCase {
        return CreditUseCase(
            getUserProfileUseCase = GetUserProfileUseCase(repository),
            updateCreditsUseCase = UpdateCreditsUseCase(repository),
            getUserCreditUseCase = GetUserCreditUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideCreditAPI(): CreditAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CreditAPI::class.java)
    }
}