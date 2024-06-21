package com.sweethome.studysmartproject.domain.model

import androidx.compose.ui.graphics.Color
import com.sweethome.studysmartproject.presentation.theme.gradient1
import com.sweethome.studysmartproject.presentation.theme.gradient2
import com.sweethome.studysmartproject.presentation.theme.gradient3
import com.sweethome.studysmartproject.presentation.theme.gradient4
import com.sweethome.studysmartproject.presentation.theme.gradient5

data class Subject(
    val name:String,
    val goalHours: Float,
    val colors: List<Color>,
    val subjectId:Int
){
    companion object{
        val subjectCardColors = listOf(gradient1, gradient2, gradient3, gradient4, gradient5)
    }
}
