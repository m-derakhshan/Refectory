package m.derakhshan.refectory.feature_authentication.domain.use_cases

import android.content.Context
import m.derakhshan.refectory.R
import m.derakhshan.refectory.core.domain.model.Request
import m.derakhshan.refectory.feature_authentication.domain.model.InvalidTaxCodeException
import m.derakhshan.refectory.feature_authentication.domain.model.UserModel
import m.derakhshan.refectory.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthenticationRepository,
    private val context: Context
) {

    @Throws
    suspend operator fun invoke(taxCode: String): Request<UserModel> {
        if (taxCode.isBlank())
            throw InvalidTaxCodeException(context.getString(R.string.login_error_empty_tax_code))
        if (taxCode.length != 16)
            throw InvalidTaxCodeException(context.getString(R.string.login_error_wrong_tax_code_length))
        return repository.login(taxCode = taxCode)
    }

}