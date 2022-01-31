package m.derakhshan.refectory.core.domain.model

sealed class Request<T>(open val data: T? = null, open val message: String? = null) {
    data class Success<T>(override val data: T) : Request<T>(data)
    //data class Loading<T>(override val data: T? = null) : Request<T>(data)
    data class Error<T>(override val message: String, override val data: T? = null) :
        Request<T>(data, message)
}
