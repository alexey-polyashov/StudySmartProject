package com.sweethome.studysmartproject.presentation.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sweethome.studysmartproject.presentation.components.DeleteDialog
import com.sweethome.studysmartproject.presentation.components.TaskCheckBox
import com.sweethome.studysmartproject.util.Priority

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(){

    var isDeleteDialogOpern by rememberSaveable {mutableStateOf(false)}

    var taskTitle by remember{ mutableStateOf("") }
    var taskDescription by remember{ mutableStateOf("") }

    var taskTitleError by rememberSaveable { mutableStateOf<String?>(null) }
    taskTitleError = when {
        taskTitle.isBlank() -> "Please enter tak title."
        taskTitle.length < 4 -> "Tak title is too short."
        taskTitle.length > 30 -> "Task title is too long."
        else -> null
    }

    DeleteDialog(
        isOpen = isDeleteDialogOpern,
        title = "Delete task?",
        bodyText = "Are you sure, you want to delete this task?",
        onDismissRequest = { isDeleteDialogOpern = false },
        onConfirmButtonClick = {isDeleteDialogOpern = false})


    Scaffold(
        topBar = {
            TaskScreenTopBar(
                isTaskExist = true,
                isComplete = false,
                checkBoxBorderColor = Color.Red,
                onBackButtonClick = { /*TODO*/ },
                onDeleteButtonClick = { isDeleteDialogOpern = true }) {

            }

        }
    )
    {
        paddingValue ->
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .padding(paddingValue)
                .padding(horizontal = 12.dp)
        ){
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = taskTitle,
                onValueChange = { taskTitle = it },
                label = { Text(text = "Title")},
                singleLine = true,
                isError = taskTitleError != null && taskTitle.isNotBlank(),
                supportingText = {Text(text = taskTitleError.orEmpty())}
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = taskDescription,
                onValueChange = { taskDescription = it},
                label = { Text(text = "Description")}
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Due date",
                style = MaterialTheme.typography.bodySmall
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "30 oct 2024",
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription =  "Select Due Date"
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Priority",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ){
                Priority.entries.forEach { priority ->
                    PriorityButton(
                        modifier = Modifier.weight(1f),
                        label = priority.title,
                        backgroundColor = priority.color,
                        borderColor = if(priority == Priority.MEDIUM){Color.White}else{Color.Transparent},
                        labelColor = if(priority == Priority.MEDIUM){Color.White}else{Color.White.copy(alpha = 0.7f)},
                        onClick = {}
                        )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Related to subject",
                style = MaterialTheme.typography.bodySmall
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "English",
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription =  "Select Subject"
                    )
                }
            }
            Button(
                enabled = taskTitleError == null,
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ){
                Text(text = "Save")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskScreenTopBar(
    isTaskExist: Boolean,
    isComplete: Boolean,
    checkBoxBorderColor: Color,
    onBackButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onCheckBoxClick: () -> Unit
    ){
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackButtonClick
            ){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate Back"
                )
            }
        },
        title = { Text(text="Task", style = MaterialTheme.typography.headlineSmall) },
        actions = {
            if(isTaskExist){
                TaskCheckBox(
                    isComplete = isComplete,
                    borderColor = checkBoxBorderColor,
                    onCheckBoxClick = onCheckBoxClick
                )
                IconButton(
                    onClick = onDeleteButtonClick
                ){
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Task"
                    )
                }
            }
        }
    )
}

@Composable
private fun PriorityButton(
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color,
    borderColor: Color,
    labelColor: Color,
    onClick: () -> Unit
){
    Box(
        modifier = modifier
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(5.dp)
            .border(1.dp, borderColor, RoundedCornerShape(5.dp))
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text = label, color = labelColor)
    }
}