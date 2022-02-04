package m.derakhshan.refectory.feature_credit.presentation.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import m.derakhshan.refectory.R
import m.derakhshan.refectory.core.presentation.plus
import m.derakhshan.refectory.ui.theme.LightBlue
import m.derakhshan.refectory.ui.theme.spacing
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun HomeScreen(
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
    ) { innerPadding ->
        Column {
            HorizontalPager(count = 2) { page ->
                Box(
                    Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                ) {
                    if (page == 1)
                        ChartCard(
                            data = mapOf(
                                Pair("03-02", 100.03f),
                                Pair("04-02", 20.01f),
                                Pair("05-02", 30.02f),
                                Pair("06-02", 40.08f),
                                Pair("07-02", 50.08f),
                                Pair("08-02", 65.08f),
                                Pair("09-02", 80.08f),
                            )
                        )
                    else
                        ChartCard(
                            data = mapOf(
                                Pair("03-02", 10.03f),
                                Pair("04-02", 2.01f),
                                Pair("05-02", 3.02f),
                                Pair("06-02", 4.08f),
                                Pair("07-02", 5.08f),
                                Pair("08-02", 6.08f),
                                Pair("09-02", 8.08f),
                            )
                        )
                }
            }
            Text(
                text = stringResource(id = R.string.restaurants),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(
                    innerPadding + MaterialTheme.spacing.small
                )
            )
        }
    }

}