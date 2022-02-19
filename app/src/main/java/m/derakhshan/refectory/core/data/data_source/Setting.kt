package m.derakhshan.refectory.core.data.data_source

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Setting @Inject constructor(
    @ApplicationContext context: Context
) {

    private val share = context.getSharedPreferences("share", Context.MODE_PRIVATE)
    private val edit = share.edit()

    var isUserLoggedIn: Boolean
        set(value) {
            edit.putBoolean("isUserLoggedIn", value)
            edit.apply()
        }
        get() = share.getBoolean("isUserLoggedIn", false)

    var lastCredit: Float
        set(value) {
            edit.putFloat("lastCredit", value)
            edit.apply()
        }
        get() = share.getFloat("lastCredit", 0f)

}