package m.derakhshan.refectory.feature_credit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.derakhshan.refectory.core.data.data_source.MyDatabase
import m.derakhshan.refectory.feature_credit.data.repository.CreditRepositoryImpl
import m.derakhshan.refectory.feature_credit.domain.repository.CreditRepository
import m.derakhshan.refectory.feature_credit.domain.use_cases.CreditUseCase
import m.derakhshan.refectory.feature_credit.domain.use_cases.GetUserProfileUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CreditModule {

    @Singleton
    @Provides
    fun provideCreditRepository(db: MyDatabase): CreditRepository {
        return CreditRepositoryImpl(db.userDao)
    }

    @Singleton
    @Provides
    fun provideCreditUseCase(repository: CreditRepository): CreditUseCase {
        return CreditUseCase(
            getUserProfileUseCase = GetUserProfileUseCase(repository)
        )
    }
}