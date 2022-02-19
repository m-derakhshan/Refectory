package m.derakhshan.refectory.feature_credit.presentation.home


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import m.derakhshan.refectory.core.data.data_source.Setting
import m.derakhshan.refectory.feature_credit.domain.model.getDetailAsMap
import m.derakhshan.refectory.feature_credit.domain.use_cases.CreditUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: CreditUseCase,
    setting: Setting
) : ViewModel() {

    private val _state = mutableStateOf(HomeViewModelState())
    val state: State<HomeViewModelState> = _state

    init {
        _state.value = _state.value.copy(
            totalCredit = "${setting.lastCredit}€"
        )
        viewModelScope.launch {
            //--------------------(show user credit in chart)--------------------//
            useCase.getUserCreditUseCase().collectLatest {
                it?.let { creditModel ->
                    _state.value = _state.value.copy(
                        creditChartData = creditModel.getDetailAsMap(),
                        mostCreditAt = creditModel.mostCreditAt
                    )
                    //--------------------(a little delay for credit animation)--------------------//
                    delay(300)
                    _state.value = _state.value.copy(
                        totalCredit = "${creditModel.totalCredit}€",
                    )
                    setting.lastCredit = creditModel.totalCredit
                }
            }
        }

        viewModelScope.launch {
            val userInfo = useCase.getUserProfileUseCase()
            _state.value = _state.value.copy(
                userImage = userInfo.photo,
                userName = "${userInfo.name} ${userInfo.surname}",
            )
            //--------------------(update credit from server)--------------------//
            useCase.updateCreditsUseCase()
        }

    }

    fun onEvent(event: HomeViewModelEvent) {
        when (event) {
            is HomeViewModelEvent.OnProfileClick -> {

            }
        }
    }
}