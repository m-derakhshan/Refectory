package m.derakhshan.refectory.feature_credit.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "Credit")
data class CreditModel(
    @PrimaryKey
    val userID: String,
    val details: List<Detail>,
    val totalCredit: Float,
)

fun CreditModel.getDetailAsMap(): Map<Any, Float> {
    val map = mutableMapOf<String, Float>()
    for (data in details) {
        map[data.date] = data.credit
    }
    return map.toMap()
}

class DetailConverter {
    @TypeConverter
    fun fromDetailString(value: String): List<Detail> {
        val type = object : TypeToken<List<Detail>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toDetailString(list: List<Detail>): String {
        return Gson().toJson(list)
    }
}