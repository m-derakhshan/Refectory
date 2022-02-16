package m.derakhshan.refectory.feature_credit.data.repository


import android.util.Log
import kotlinx.coroutines.flow.Flow
import m.derakhshan.refectory.feature_authentication.data.data_source.dao.UserDao
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_credit.data.data_source.CreditAPI
import m.derakhshan.refectory.feature_credit.data.data_source.dao.CreditDao
import m.derakhshan.refectory.feature_credit.data.data_source.dto.toCreditModel
import m.derakhshan.refectory.feature_credit.domain.model.CreditModel
import m.derakhshan.refectory.feature_credit.domain.repository.CreditRepository
import javax.inject.Inject

class CreditRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userCredit: CreditDao,
    private val creditAPI: CreditAPI
) : CreditRepository {

    override suspend fun getUserInfo(): UserModel {
        return userDao.userInfo()
    }

    override suspend fun updateUserCredit() {
        try {
            val credit = creditAPI.getCredits().toCreditModel()
            userCredit.deleteAll()
            userCredit.insert(credit)
        } catch (e: Exception) {
            Log.i("Log", "updateUserCredit: error in CreditRepositoryImpl :${e.message}")
        }
    }

    override fun getUserCredit(): Flow<CreditModel?> {
        return userCredit.getCredit()
    }
}