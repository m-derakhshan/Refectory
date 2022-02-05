package m.derakhshan.refectory.feature_credit.presentation.home.composable


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import m.derakhshan.refectory.R
import m.derakhshan.refectory.core.presentation.plus
import m.derakhshan.refectory.feature_credit.presentation.home.HomeViewModel
import m.derakhshan.refectory.feature_credit.presentation.home.HomeViewModelEvent
import m.derakhshan.refectory.ui.theme.LightBlue
import m.derakhshan.refectory.ui.theme.spacing
import kotlin.math.absoluteValue

@InternalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState, block = {
        snapshotFlow { pagerState.currentPage }.collectLatest { page ->
            viewModel.onEvent(HomeViewModelEvent.CardPositionChanged(page))
        }
    })

    Scaffold(
        scaffoldState = scaffoldState,
    ) { innerPadding ->
        Column {
            HorizontalPager(count = 2, state = pagerState) { page ->
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
                    if (page == 0)
                        ProfileCard(
                            credit = state.userCredit,
                            name = state.userName,
                            taxCode = state.userTaxCode,
                            image = state.userImage
                        )
                    else ChartCard(data = state.creditChartData)
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                for (i in 0..1) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(if (state.cardPosition == i) LightBlue else MaterialTheme.colors.background)
                            .border(
                                width = 1.dp,
                                shape = CircleShape,
                                color = MaterialTheme.colors.onBackground
                            )
                    )
                    Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
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