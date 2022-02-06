package m.derakhshan.refectory.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import m.derakhshan.refectory.feature_authentication.data.data_source.dao.UserDao
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel


@Database(
    entities = [UserModel::class],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}