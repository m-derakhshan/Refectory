package m.derakhshan.refectory.feature_credit.data.data_source


import m.derakhshan.refectory.feature_credit.data.data_source.dto.CreditServerModel
import retrofit2.http.GET

interface CreditAPI {

    @GET("/credits")
    suspend fun getCredits(): CreditServerModel
}