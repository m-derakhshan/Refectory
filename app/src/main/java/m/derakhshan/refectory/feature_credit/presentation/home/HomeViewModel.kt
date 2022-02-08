package m.derakhshan.refectory.feature_credit.presentation.home


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import m.derakhshan.refectory.feature_credit.domain.model.getDetailAsMap
import m.derakhshan.refectory.feature_credit.domain.use_cases.CreditUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: CreditUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeViewModelState())
    val state: State<HomeViewModelState> = _state

    init {
        viewModelScope.launch {
            val userInfo = useCase.getUserProfileUseCase()
            _state.value = _state.value.copy(
                userImage = userInfo.photo,
                userName = userInfo.name,
                userTaxCode = userInfo.taxCode,
            )
            //--------------------(update credit from server)--------------------//
            useCase.updateCreditsUseCase()
            //--------------------(show user credit in chart)--------------------//
            useCase.getUserCreditUseCase().collect { creditModel ->
                _state.value = _state.value.copy(
                    creditChartData = creditModel.getDetailAsMap(),
                )
                var tempCredit = creditModel.totalCredit
                if (tempCredit > 5) {
                    tempCredit -= 5
                    for (i in 1..6) {
                        _state.value = _state.value.copy(userCredit = tempCredit)
                        tempCredit += 1
                        delay(100)
                    }
                }
            }
        }
    }

    fun onEvent(event: HomeViewModelEvent) {
        when (event) {
            is HomeViewModelEvent.CardPositionChanged -> {
                _state.value = _state.value.copy(
                    cardPosition = event.position
                )
            }
        }
    }
}