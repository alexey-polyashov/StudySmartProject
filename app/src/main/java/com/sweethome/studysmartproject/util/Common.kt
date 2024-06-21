package com.sweethome.studysmartproject.util

import androidx.compose.ui.graphics.Color
import com.sweethome.studysmartproject.presentation.theme.Green
import com.sweethome.studysmartproject.presentation.theme.Orange
import com.sweethome.studysmartproject.presentation.theme.Red

enum class Priority(val title: String, val color: Color, val value: Int){

    LOW("Low", color = Green, value = 0),
    MEDIUM("Medium", color = Orange, value = 1),
    HIGH("High", color = Red, value = 2);

    companion object{
        fun fromInt(value:Int) = values().firstOrNull{it.value == value} ?: MEDIUM
    }

}