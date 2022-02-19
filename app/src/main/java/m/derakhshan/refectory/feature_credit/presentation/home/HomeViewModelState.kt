package m.derakhshan.refectory.feature_credit.presentation.home

data class HomeViewModelState(
    val totalCredit: String = "",
    val userImage: String = "",
    val userName: String = "",
    val creditChartData: Map<Any, Float> = emptyMap(),
    val mostCreditAt: String = ""
)
