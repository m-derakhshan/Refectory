package m.derakhshan.refectory.feature_authentication.data.data_source.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserModel)

    @Delete
    suspend fun delete(user: UserModel)

    @Query("SELECT * FROM USER LIMIT 1")
    suspend fun userInfo(): UserModel
}