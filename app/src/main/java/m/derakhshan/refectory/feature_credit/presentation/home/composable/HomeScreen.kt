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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import m.derakhshan.refectory.R
import m.derakhshan.refectory.feature_credit.presentation.home.HomeViewModel
import m.derakhshan.refectory.ui.theme.DarkBlue
import m.derakhshan.refectory.ui.theme.VeryLightBlue
import m.derakhshan.refectory.ui.theme.spacing

@OptIn(ExperimentalAnimationApi::class,ExperimentalPagerApi::class)
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
                    .background(VeryLightBlue)
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
                Chart(
                    data = state.creditChartData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }


        }
    }

}