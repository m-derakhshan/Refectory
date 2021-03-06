package m.derakhshan.refectory.feature_authentication.data.data_source


import m.derakhshan.refectory.feature_authentication.data.data_source.dto.UserServerModel
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationAPI {

    @POST("users/login")
    suspend fun login(@Body taxCode: String): UserServerModel

    @POST("users/sign_up")
    suspend fun signUp(@Body user: UserModel): UserServerModel
}