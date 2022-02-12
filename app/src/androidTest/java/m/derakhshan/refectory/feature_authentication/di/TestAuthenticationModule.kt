package m.derakhshan.refectory.feature_authentication.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import m.derakhshan.refectory.core.data.data_source.MyDatabase
import m.derakhshan.refectory.core.data.data_source.Setting
import m.derakhshan.refectory.core.domain.utils.Constants
import m.derakhshan.refectory.feature_authentication.data.data_source.AuthenticationAPI
import m.derakhshan.refectory.feature_authentication.data.data_source.FakeAuthenticationAPIAndroidTest
import m.derakhshan.refectory.feature_authentication.data.repository.FakeAuthenticationRepositoryAndroidTest
import m.derakhshan.refectory.feature_authentication.data.repository.FakeAuthenticationRepositoryImplAndroidTest
import m.derakhshan.refectory.feature_authentication.domain.repository.AuthenticationRepository
import m.derakhshan.refectory.feature_authentication.domain.use_cases.AuthenticationUseCase
import m.derakhshan.refectory.feature_authentication.domain.use_cases.LoginUseCase
import m.derakhshan.refectory.feature_authentication.domain.use_cases.SignUpUseCase
import m.derakhshan.refectory.feature_authentication.domain.use_cases.StoreUserDataInDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AndroidTestAuthenticationModule {


    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MyDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            MyDatabase::class.java,
        ).build()
    }


    @Singleton
    @Provides
    fun provideAuthenticationAPI(): AuthenticationAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FakeAuthenticationAPIAndroidTest::class.java)
    }


    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        api: FakeAuthenticationAPIAndroidTest,
        db: MyDatabase
    ): AuthenticationRepository {
        return FakeAuthenticationRepositoryImplAndroidTest(api = api, userDao = db.userDao)
    }


    @Singleton
    @Provides
    fun provideAuthenticationUseCase(repository: AuthenticationRepository): AuthenticationUseCase {
        return AuthenticationUseCase(
            loginUseCase = LoginUseCase(repository = repository),
            signUpUseCase = SignUpUseCase(repository = repository),
            storeUserDataInDatabase = StoreUserDataInDatabase(repository = repository)
        )
    }
}