package com.dorbom.compose_study.util

import android.content.res.Resources
import android.content.res.Resources.getSystem
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Display {

    fun Int.toDp(): Float = (this / Resources.getSystem().displayMetrics.density)
}