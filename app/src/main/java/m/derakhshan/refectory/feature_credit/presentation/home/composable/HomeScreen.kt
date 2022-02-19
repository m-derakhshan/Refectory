package m.derakhshan.refectory.feature_credit.presentation.home.composable


import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import m.derakhshan.refectory.R
import m.derakhshan.refectory.feature_credit.presentation.home.HomeViewModel
import m.derakhshan.refectory.ui.theme.DarkBlue
import m.derakhshan.refectory.ui.theme.VeryLightGrayBlue
import m.derakhshan.refectory.ui.theme.spacing


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                    .background(MaterialTheme.colors.surface)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.weekly_report),
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.padding(MaterialTheme.spacing.small)
                    )
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(id = R.string.profile),
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.small)
                            .size(50.dp)
                            .clip(CircleShape)
                            .border(width = 2.dp, shape = CircleShape, color = DarkBlue)
                    )
                }
                // TODO: the color of this component is constant
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(VeryLightGrayBlue, RoundedCornerShape(10.dp))
                        .padding(15.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = state.userName,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Center,
                    )
                }
                Chart(
                    data = state.creditChartData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    barWidth = 60f
                )
            }
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .width(160.dp)
                        .height(120.dp)
                        .shadow(2.dp, RoundedCornerShape(10.dp))
                        .background(
                            MaterialTheme.colors.secondary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.total_credit),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AnimatedContent(
                            targetState = state.totalCredit,
                            transitionSpec = {
                                if (targetState > initialState) {
                                    slideInVertically { height -> height } + fadeIn() with
                                            slideOutVertically { height -> -height } + fadeOut()
                                } else {
                                    slideInVertically { height -> -height } + fadeIn() with
                                            slideOutVertically { height -> height } + fadeOut()
                                }.using(
                                    SizeTransform(clip = false)
                                )
                            }
                        ) { targetCount ->
                            Text(
                                text = targetCount,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onSecondary,
                                style = MaterialTheme.typography.body1,
                            )
                        }
                    }


                }

                Column(
                    modifier = Modifier
                        .width(160.dp)
                        .height(120.dp)
                        .shadow(2.dp, RoundedCornerShape(10.dp))
                        .background(
                            MaterialTheme.colors.surface,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.most_credit_at),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = state.mostCreditAt,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.body1,
                        )
                    }

                }

            }

        }
    }

}