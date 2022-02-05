package m.derakhshan.refectory.feature_credit.presentation.home


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(HomeViewModelState())
    val state: State<HomeViewModelState> = _state

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