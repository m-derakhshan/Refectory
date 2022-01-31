package m.derakhshan.refectory.feature_authentication.domain.use_cases

import m.derakhshan.refectory.core.domain.model.Request
import m.derakhshan.refectory.core.domain.model.UserModel
import m.derakhshan.refectory.feature_authentication.domain.model.InvalidTaxCodeException
import m.derakhshan.refectory.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class LoginUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {

    @Throws
    suspend operator fun invoke(taxCode: String): Request<UserModel> {
        if (taxCode.isBlank())
            throw InvalidTaxCodeException("Tax Code can't left blank.")
        if (taxCode.length != 16)
            throw InvalidTaxCodeException("Length of Tax Code is not correct.")
        return repository.login(taxCode = taxCode)
    }

}