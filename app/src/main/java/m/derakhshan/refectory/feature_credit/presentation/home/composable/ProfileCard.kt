package m.derakhshan.refectory.feature_credit.presentation.home.composable

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
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
    image: String,
    setting: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(15.dp)
            .shadow(2.dp, shape = RoundedCornerShape(20.dp))
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
                    }), contentDescription = "Profile",
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, MaterialTheme.colors.onBackground, shape = CircleShape)
            )
            Row(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.credit),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.padding(MaterialTheme.spacing.extraSmall))
                AnimatedContent(targetState = credit,
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
                    }) {
                    Text(
                        text = credit.toString(),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body1,
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = taxCode,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1,
            )
            IconButton(onClick = setting) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "setting")
            }
        }
    }
}