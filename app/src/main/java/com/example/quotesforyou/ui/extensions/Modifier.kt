package com.example.quotesforyou.ui.extensions

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

//Using composed for making shimmer stateful
fun Modifier.shimmerEffect(colors: List<Color>): Modifier = composed {

    //initial size zero
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    //Add transition
    val transitions = rememberInfiniteTransition()

    //get offset
    val startOffset by transitions.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    //animate background with shimmer
    background(
        //liner gradient for diagonal shimmer effect
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(startOffset, 0.toFloat()),
            end = Offset(startOffset + size.width.toFloat(), size.height.toFloat())

        )
    ).onGloballyPositioned {
        size = it.size
    }
}