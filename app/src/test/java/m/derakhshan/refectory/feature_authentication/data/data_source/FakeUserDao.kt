package m.derakhshan.refectory.feature_authentication.data.data_source

import m.derakhshan.refectory.feature_authentication.data.data_source.dao.UserDao
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel

class FakeUserDao : UserDao {

    val fakeDatabase = mutableListOf<UserModel>()

    override suspend fun insert(user: UserModel) {
        fakeDatabase.add(user)
    }

    override suspend fun delete(user: UserModel) {
        fakeDatabase.remove(user)
    }

    override suspend fun userInfo(): UserModel {
        return fakeDatabase.first()
    }
}