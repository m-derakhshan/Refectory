package m.derakhshan.refectory.feature_credit.data.repository

import kotlinx.coroutines.flow.Flow
import m.derakhshan.refectory.feature_authentication.data.data_source.dao.UserDao
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_credit.domain.repository.CreditRepository
import javax.inject.Inject

class CreditRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : CreditRepository {

    override suspend fun getUserInfo(): UserModel {
        return userDao.userInfo()
    }
}