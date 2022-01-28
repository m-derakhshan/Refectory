package m.derakhshan.refectory.feature_authentication.presentation.authentication.composable


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import m.derakhshan.refectory.ui.theme.spacing

@Composable
fun Version(version: String, modifier: Modifier = Modifier) {

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((1.5).dp)
                .alpha(0.4f)
                .background(MaterialTheme.colors.onBackground)
                .align(Alignment.Center)
        )
        Text(
            text = "Version $version",
            style = MaterialTheme.typography.caption,
            modifier = modifier
                .align(
                    Alignment.Center
                )
                .background(MaterialTheme.colors.background)
                .padding(MaterialTheme.spacing.small)
        )
    }
}