package m.derakhshan.refectory.feature_credit.presentation.home.composable


import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import m.derakhshan.refectory.R
import m.derakhshan.refectory.feature_credit.presentation.home.HomeViewModel
import m.derakhshan.refectory.ui.theme.*

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
                        text = "Mohammad Derakhshan Talkhouncheh",
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
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.total_credit),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = "120E",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()

                    )

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
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = "Jamm Restaurant and Bar",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()

                    )

                }


            }


        }
    }

}