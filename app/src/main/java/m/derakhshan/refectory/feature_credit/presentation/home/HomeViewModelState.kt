package m.derakhshan.refectory.feature_credit.presentation.home

data class HomeViewModelState(
    val userCredit: Float = 50f,
    val userImage: String = "https://images.hindustantimes.com/img/2021/06/08/550x309/193197574_307878937618749_8630090066141767698_n_1623137800420_1623137811962.jpg",
    val userName: String = "Mohammad Derakhshan Talkhouncheh",
    val userTaxCode: String = "DRKMMM98B24Z274",
    val creditChartData: Map<Any, Float> = mapOf(
        Pair("02-02", 10f), Pair("03-02", 12f), Pair("04-02", 5f),
        Pair("05-02", 11f), Pair("06-02", 2f), Pair("07-02", 6f),
        Pair("08-02", 13f)
    ),
    val cardPosition: Int = 0
)
