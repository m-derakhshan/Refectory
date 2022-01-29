package m.derakhshan.refectory.feature_authentication.domain.use_cases

import kotlinx.coroutines.delay
import m.derakhshan.refectory.core.data.model.Request
import m.derakhshan.refectory.core.data.model.UserModel
import m.derakhshan.refectory.feature_authentication.domain.model.InvalidTaxCodeException

class LoginUseCase {

    @Throws
    suspend operator fun invoke(taxCode: String): Request<UserModel> {
        if (taxCode.isBlank())
            throw InvalidTaxCodeException("Tax Code can't left blank.")

        // TODO: implement login logic here and remove below lines 
        delay(2000)
        return Request.Success(UserModel(taxCode = taxCode))
    }

}