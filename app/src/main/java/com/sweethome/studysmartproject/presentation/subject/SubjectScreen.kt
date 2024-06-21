package com.sweethome.studysmartproject.presentation.subject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sweethome.studysmartproject.domain.model.Subject
import com.sweethome.studysmartproject.presentation.components.AddSubjectDialog
import com.sweethome.studysmartproject.presentation.components.CountCard
import com.sweethome.studysmartproject.presentation.components.DeleteDialog
import com.sweethome.studysmartproject.presentation.components.studySessionList
import com.sweethome.studysmartproject.presentation.components.taskList
import com.sweethome.studysmartproject.sessions
import com.sweethome.studysmartproject.tasks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreen(

) {

    val listState = rememberLazyListState()
    val isFABExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    var isEditSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }

    var subjectName by remember { mutableStateOf("") }
    var goalHours by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf( Subject.subjectCardColors.random()) }

    AddSubjectDialog(
        isOpen = isEditSubjectDialogOpen,
        selectedColors = selectedColor,
        subjectName = subjectName,
        goalHours = goalHours,
        onDismissRequest = { isEditSubjectDialogOpen = false},
        onConfirmButtonClick = {
            isEditSubjectDialogOpen = false
        },
        onColorChange = {selectedColor = it},
        onGoalHoursChange = {goalHours = it},
        onSubjectNameChange = {subjectName = it},
    )

    DeleteDialog(
        isOpen = isDeleteSubjectDialogOpen,
        title = "Delete Subject?",
        bodyText = "Are you sure, you want to delete thus subject? All related " +
                "tasks and study sessions will be permanently removed. This action can not be undo.",
        onDismissRequest = { isDeleteSubjectDialogOpen = false },
        onConfirmButtonClick = { isDeleteSubjectDialogOpen = false }
    )

    DeleteDialog(
        isOpen = isDeleteSessionDialogOpen,
        title = "Delete session?",
        bodyText = "Are you sure, you want to delete thus sessiob? Your studied hours will be redused" +
                "by this session time. This action can not be undo.",
        onDismissRequest = { isDeleteSessionDialogOpen = false },
        onConfirmButtonClick = { isDeleteSessionDialogOpen = false }
    )


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SubjectScreenTopBar(
                title = "English",
                onBackButtonClick = {},
                onDeleteButtonClick = {isDeleteSubjectDialogOpen = true},
                onEditButtonClick = {isEditSubjectDialogOpen = true},
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { },
                icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "Add") },
                text = { Text(text = "Add Task")},
                expanded = isFABExpanded
            )
        }
    ){
        paddingValue ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            item{
                SubjectOverviewSection(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    studiedHours = "10",
                    goalHours = "15",
                    progress = 0.74f

                )
            }
            taskList(
                sectionTitle = "UPCOMING TASK",
                emptyListText = "You don't have any apcoming tasks.\n" +
                        "Click the + to add new task.",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )
            item{
                Spacer(modifier = Modifier.height(20.dp))
            }
            taskList(
                sectionTitle = "COMPLETED TASK",
                emptyListText = "You don't have any apcoming tasks.\n" +
                        "Click the check box on completion task.",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )
            item{
                Spacer(modifier = Modifier.height(20.dp))
            }
            studySessionList(
                sectionTitle = "RECENT STUDY SESSION",
                emptyListText = "You don't have any completed study sessions.\n" +
                        "Start a study session to begin recording your progress.",
                sessions = sessions,
                onDeleteIconClick = { isDeleteSessionDialogOpen=true }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubjectScreenTopBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit
){
    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {
             IconButton(onClick = onBackButtonClick) {
                 Icon(
                     imageVector = Icons.Default.ArrowBack,
                     contentDescription = "Navigate Back"
                 )
             }
        },
        title = { Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineSmall
        ) },
        actions = {
            IconButton(onClick = onDeleteButtonClick){
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Subject"
                )
            }
            IconButton(onClick = onEditButtonClick){
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Subject"
                )
            }
        }
    )
}

@Composable
fun SubjectOverviewSection(
    modifier : Modifier,
    studiedHours: String,
    goalHours: String,
    progress: Float
){
    val percentageProgress = remember(progress){
        (progress * 100).toInt().coerceIn(0, 100)
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Study Hours",
            count = goalHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Study Hours",
            count = studiedHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier.size(75.dp),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = 1f,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = progress,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round
            )
            Text(
                text = "$percentageProgress%"
            )
        }
    }
}