package m.derakhshan.refectory.feature_credit.presentation.home.composable

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
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
    horizontalSpace: Float = 150f
) {

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize(), onDraw = {


            val width = size.width
            val height = size.height
            val maxHeight = data.values.maxOrNull() ?: return@Canvas
            val scale = height / maxHeight
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
                        height = (data[key]!! * scale) - textSize
                    )
                )
                drawLine(
                    color = horizontalLineColor,
                    start = Offset(horizontalSpace*0.8f, height - scale * data[key]!!),
                    end = Offset(
                        space + lineWidth,
                        height - scale * data[key]!!
                    ),
                    strokeWidth = horizontalLineWidth
                )

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

                paint.textAlign = Paint.Align.LEFT
                this.drawContext.canvas.nativeCanvas.drawText(
                    "${data[key]}€",
                    0f,
                    height - scale * data[key]!! + 11,
                    paint
                )
                space += (width - horizontalSpace) / (data.keys.size - 1)
            }

        })

    }


}