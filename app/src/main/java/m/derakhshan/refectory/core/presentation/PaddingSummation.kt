package m.derakhshan.refectory.core.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection


operator fun PaddingValues.plus(space: Dp): PaddingValues {
    val padding = PaddingValues(space)
    return PaddingValues(
        start =
        calculateStartPadding(LayoutDirection.Ltr) + padding.calculateStartPadding(LayoutDirection.Ltr),
        end = calculateEndPadding(LayoutDirection.Ltr) + padding.calculateEndPadding(LayoutDirection.Ltr),
        top = calculateTopPadding() + padding.calculateTopPadding(),
        bottom = calculateBottomPadding() + padding.calculateBottomPadding()
    )
}