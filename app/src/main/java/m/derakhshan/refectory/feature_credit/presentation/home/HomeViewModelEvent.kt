package m.derakhshan.refectory.feature_credit.presentation.home

sealed class HomeViewModelEvent {
    data class CardPositionChanged(val position: Int) : HomeViewModelEvent()
}
