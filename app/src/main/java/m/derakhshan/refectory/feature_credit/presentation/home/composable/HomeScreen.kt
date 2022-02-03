package m.derakhshan.refectory.feature_credit.presentation.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import m.derakhshan.refectory.ui.theme.LightBlue
import m.derakhshan.refectory.ui.theme.LightYellow

@Composable
fun HomeScreen(
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(15.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(LightBlue)
                    .padding(25.dp)
            ) {
                BarChart(
                    modifier = Modifier.fillMaxSize(),
                    lineWidth = 10f,
                    textSize = 35f,
                    data = mapOf(
                        Pair("03-02", 10.03f),
                        Pair("04-02", 20.01f),
                        Pair("05-02", 30.02f),
                        Pair("06-02", 40.08f),
                        Pair("07-02", 50.08f),
                        Pair("08-02", 20.08f),
                        Pair("09-02", 80.08f),
                    )
                )
            }

        }
    }

}