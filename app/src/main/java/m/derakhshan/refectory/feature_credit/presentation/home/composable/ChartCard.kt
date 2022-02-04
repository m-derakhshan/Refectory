package m.derakhshan.refectory.feature_credit.presentation.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import m.derakhshan.refectory.ui.theme.LightBlue

@Composable
fun ChartCard(
    data: Map<Any, Float>
) {
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
            data = data
        )
    }
}