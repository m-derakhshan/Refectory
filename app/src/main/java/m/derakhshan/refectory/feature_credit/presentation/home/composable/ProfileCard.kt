package m.derakhshan.refectory.feature_credit.presentation.home.composable

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.delay
import m.derakhshan.refectory.R
import m.derakhshan.refectory.ui.theme.LightBlue
import m.derakhshan.refectory.ui.theme.spacing

@ExperimentalAnimationApi
@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    credit: Float,
    name: String,
    taxCode: String,
    image: String
) {
    var creditAnimation by remember {
        mutableStateOf(if (credit - 5 > 0) credit - 5 else credit)
    }
    LaunchedEffect(true, block = {
        while (creditAnimation < credit) {
            delay(100)
            creditAnimation += 1
        }
    })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(15.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(LightBlue)
            .padding(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = rememberImagePainter(
                    data = image,
                    builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }), contentDescription = "Profile", modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, MaterialTheme.colors.onBackground, shape = CircleShape)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.credit),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
                AnimatedContent(targetState = creditAnimation, transitionSpec = {
                    if (targetState > initialState) {
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        SizeTransform(clip = false)
                    )
                }) { creditAmount ->
                    Text(text = creditAmount.toString(), style = MaterialTheme.typography.body1)
                }

            }
        }
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = taxCode,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body1
        )
    }
}