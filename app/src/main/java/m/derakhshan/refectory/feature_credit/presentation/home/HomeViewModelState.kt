package m.derakhshan.refectory.feature_credit.presentation.home

data class HomeViewModelState(
    val userCredit: Float = 0f,
    val userImage: String = "",
    val userName: String = "",
    val userTaxCode: String = "",
    val creditChartData: Map<Any, Float> = mapOf(
        Pair("02-02", 10f), Pair("03-02", 12f), Pair("04-02", 5f),
        Pair("05-02", 11f), Pair("06-02", 2f), Pair("07-02", 6f),
        Pair("08-02", 13f)
    ),
    val cardPosition: Int = 0
)
