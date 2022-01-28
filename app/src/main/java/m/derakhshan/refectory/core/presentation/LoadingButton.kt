package m.derakhshan.refectory.core.presentation

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp


@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    isExpanded: Boolean,
    callBack: () -> Unit
) {

    val infiniteAnimation = rememberInfiniteTransition()
    val rotation by infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = if (isExpanded) 720f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        )
    )

    val myModifier = if (isExpanded)
        modifier
            .fillMaxWidth()
            .height(50.dp)
    else
        modifier
            .width(40.dp)
            .height(50.dp)
            .rotate(rotation)

    Button(
        onClick = callBack, modifier = myModifier
    ) {
        Text(text = if (isExpanded) buttonText else "")
    }


}