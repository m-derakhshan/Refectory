package m.derakhshan.refectory.feature_credit.presentation.home

data class HomeViewModelState(
    val userCredit: Float = 0f,
    val userImage: String = "",
    val userName: String = "",
    val userTaxCode: String = "",
    val creditChartData: Map<Any, Float> = emptyMap(),
    val cardPosition: Int = 0
)
