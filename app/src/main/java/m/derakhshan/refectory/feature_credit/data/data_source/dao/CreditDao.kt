package m.derakhshan.refectory.feature_credit.data.data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import m.derakhshan.refectory.feature_credit.domain.model.CreditModel

@Dao
interface CreditDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(credit: CreditModel)


    @Query("DELETE FROM Credit")
    suspend fun deleteAll()

    @Query("SELECT * FROM Credit LIMIT 1")
    fun getCredit(): Flow<CreditModel?>

}