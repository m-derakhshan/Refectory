package m.derakhshan.refectory.feature_credit.presentation.home.composable

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb


@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    data: Map<Any, Float>,
    lineWidth: Float = 5f,
    textSize: Float = 34f,
    textColor: Color = MaterialTheme.colors.onBackground,
    horizontalLineColor: Color = MaterialTheme.colors.onBackground,
    horizontalLineWidth: Float = 1f,
    barColor: Color = MaterialTheme.colors.onBackground,
    horizontalSpace: Float = 150f,
    chartLineColor: Color = MaterialTheme.colors.onBackground
) {

    Box(modifier = modifier) {

        val coefficient = remember {
            Animatable(0f)
        }
        LaunchedEffect(coefficient) {
            coefficient.animateTo(1f, animationSpec = tween(3000))
        }
        Canvas(modifier = Modifier.matchParentSize(), onDraw = {
            val width = size.width
            val height = size.height
            val maxHeight = data.values.maxOrNull() ?: return@Canvas
            val scale = (height / maxHeight) * coefficient.value
            var space = horizontalSpace
            for (key in data.keys) {
                drawRect(
                    color = barColor,
                    topLeft = Offset(
                        x = space,
                        y = height - scale * data[key]!!
                    ),
                    size = Size(
                        width = lineWidth,
                        height = if (data[key]!! * scale > 0) data[key]!! * scale - textSize else 0f
                    )
                )
                //--------------------(chart grid horizontal lines)--------------------//
                drawLine(
                    color = horizontalLineColor,
                    start = Offset(
                        horizontalSpace * 0.9f,
                        if (scale * data[key]!! > 0) height - scale * data[key]!! else 0f
                    ),
                    end = Offset(
                        space + lineWidth,
                        if (scale * data[key]!! > 0) height - scale * data[key]!! else 0f
                    ),
                    strokeWidth = horizontalLineWidth
                )

                //--------------------(chart X labels)--------------------//
                val paint = Paint()
                paint.textAlign = Paint.Align.CENTER
                paint.textSize = textSize
                paint.color = textColor.toArgb()
                this.drawContext.canvas.nativeCanvas.drawText(
                    key.toString(),
                    space,
                    height,
                    paint
                )

                //--------------------(chart Y labels)--------------------//
                paint.textAlign = Paint.Align.LEFT
                this.drawContext.canvas.nativeCanvas.drawText(
                    "${data[key]}€",
                    0f,
                    height - scale * data[key]!! + 11,
                    paint
                )
                space += (width - horizontalSpace) / (data.keys.size - 1)
            }
            //--------------------(chart vertical line)--------------------//
            drawLine(
                color = chartLineColor,
                start = Offset(horizontalSpace * 0.9f, -(textSize * 0.5f)),
                end = Offset(horizontalSpace * 0.9f, height - textSize),
                strokeWidth = lineWidth * 0.35f
            )
            //--------------------(chart horizontal line)--------------------//
            drawLine(
                color = chartLineColor,
                start = Offset(horizontalSpace * 0.7f, height - textSize),
                end = Offset(width + 40, height - textSize),
                strokeWidth = lineWidth * 0.35f
            )
        })

    }


}