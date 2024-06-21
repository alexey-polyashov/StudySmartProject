package com.sweethome.studysmartproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sweethome.studysmartproject.domain.model.Session
import com.sweethome.studysmartproject.domain.model.Subject
import com.sweethome.studysmartproject.domain.model.Task
import com.sweethome.studysmartproject.presentation.dashboard.DashboardScreen
import com.sweethome.studysmartproject.presentation.subject.SubjectScreen
import com.sweethome.studysmartproject.presentation.task.TaskScreen
import com.sweethome.studysmartproject.presentation.theme.StudySmartProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudySmartProjectTheme {
                // A surface container using the 'background' color from the theme
                //DashboardScreen()
                //SubjectScreen()
                TaskScreen()
            }
        }
    }
}

val subjects = listOf(
    Subject(name = "English", goalHours = 10f, colors = Subject.subjectCardColors[0], subjectId = 1),
    Subject(name = "Physics", goalHours = 10f, colors = Subject.subjectCardColors[1], subjectId = 2),
    Subject(name = "Math", goalHours = 10f, colors = Subject.subjectCardColors[2], subjectId = 3),
    Subject(name = "Geography", goalHours = 10f, colors = Subject.subjectCardColors[3], subjectId = 4),
    Subject(name = "Fine Arts", goalHours = 10f, colors = Subject.subjectCardColors[4], subjectId = 5),
)
val tasks = listOf(
    Task(title = "Prepare notes", description = "", dueDate = 0L, priority = 0, relatedToSubject = "", isComplete = false, taskId = 1, taskSubjectId = 1),
    Task(title = "Do homework", description = "", dueDate = 0L, priority = 2, relatedToSubject = "", isComplete = true, taskId = 2, taskSubjectId = 2),
    Task(title = "Go coaching", description = "", dueDate = 0L, priority = 1, relatedToSubject = "", isComplete = false, taskId = 3, taskSubjectId = 4),
    Task(title = "Assistment", description = "", dueDate = 0L, priority = 1, relatedToSubject = "", isComplete = false, taskId = 4, taskSubjectId = 5),
    Task(title = "Write poem", description = "", dueDate = 0L, priority = 0, relatedToSubject = "", isComplete = true, taskId = 5, taskSubjectId = 3),
)
val sessions = listOf(
    Session(sessionSubjectId = 1, relatedToSubject = "English", date = 1L, duration = 1L, sessionId = 0),
    Session(sessionSubjectId = 2, relatedToSubject = "Physics", date = 1L, duration = 1L, sessionId = 1),
    Session(sessionSubjectId = 3, relatedToSubject = "Geography", date = 1L, duration = 1L, sessionId = 2),
    Session(sessionSubjectId = 3, relatedToSubject = "Math", date = 1L, duration = 1L, sessionId = 3),
)
