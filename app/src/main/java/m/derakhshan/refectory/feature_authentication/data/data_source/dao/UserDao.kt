package m.derakhshan.refectory.feature_authentication.data.data_source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserModel)

    @Delete
    suspend fun delete(user: UserModel)
}