package com.sweethome.studysmartproject.domain.model

import androidx.compose.ui.graphics.Color
import com.sweethome.studysmartproject.presentation.theme.gradient1
import com.sweethome.studysmartproject.presentation.theme.gradient2
import com.sweethome.studysmartproject.presentation.theme.gradient3
import com.sweethome.studysmartproject.presentation.theme.gradient4
import com.sweethome.studysmartproject.presentation.theme.gradient5

data class Task(
    val title:String,
    val description:String,
    val dueDate: Long,
    val priority: Int,
    val relatedToSubject: String,
    val taskSubjectId: Int,
    val isComplete: Boolean,
    val taskId: Int
)
