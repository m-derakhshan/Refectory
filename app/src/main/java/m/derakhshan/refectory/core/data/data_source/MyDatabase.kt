package m.derakhshan.refectory.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import m.derakhshan.refectory.feature_authentication.data.data_source.dao.UserDao
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_credit.data.data_source.dao.CreditDao
import m.derakhshan.refectory.feature_credit.domain.model.CreditModel
import m.derakhshan.refectory.feature_credit.domain.model.DetailConverter


@Database(
    entities = [UserModel::class, CreditModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DetailConverter::class,
)
abstract class MyDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val creditDao: CreditDao
}