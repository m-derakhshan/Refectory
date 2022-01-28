package m.derakhshan.refectory.feature_authentication.presentation.authentication.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import m.derakhshan.refectory.R


@Composable
fun LottieBanner(modifier: Modifier = Modifier) {

    val lottieComposition by
    rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.authentication_banner))
    val lottieProgress by animateLottieCompositionAsState(
        composition = lottieComposition, iterations = LottieConstants.IterateForever
    )
    Box(modifier = modifier) {
        LottieAnimation(
            composition = lottieComposition,
            progress = lottieProgress,
            alignment = Alignment.Center
        )
    }

}