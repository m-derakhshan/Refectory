package m.derakhshan.refectory.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import m.derakhshan.refectory.R

// Set of Material typography styles to start with

val fancyFont = FontFamily(
    Font(
        R.font.welcome_font,
        weight = FontWeight.Normal
    )
)

val attractiveFont = FontFamily(Font(R.font.attractive))

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = attractiveFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = attractiveFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = attractiveFont,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = attractiveFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    /* Other default text styles to override

    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)