package com.example.to_dolist

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_dolist.MyDatePicker
import java.util.Calendar
import java.text.SimpleDateFormat
import androidx.navigation.NavController
import java.util.Date

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreen(navController: NavController) {
    var textFieldTitle by remember { mutableStateOf("") }
    var textFieldDesc by remember { mutableStateOf("") }
    var selectedDate by rememberSaveable { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf("") }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            textFieldTitle = ""
            textFieldDesc = ""
            if (navController.currentBackStack.value.size >= 2) {
                navController.navigate(Screen.Home.route)
            }
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }
        IconButton(onClick = {
            navController.navigateUp()
        }) {
            Icon(imageVector = Icons.Default.Check, contentDescription = "Checkmark")
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                textFieldTitle = ""
                textFieldDesc = ""
                if (navController.currentBackStack.value.size >= 2) {
                    navController.navigate(Screen.Home.route)
                }
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Checkmark")
            }
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = textFieldTitle,
            onValueChange = { textFieldTitle = it },
            placeholder = { Text(text = "Title") }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = textFieldDesc,
            onValueChange = { textFieldDesc = it },
            placeholder = { Text(text = "Description") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MyDatePicker(onDateSelected = { date ->
                selectedDate = date
            })

            Spacer(modifier = Modifier.width(25.dp))

            MyPriority(onPriSelected = { priority ->
                selectedPriority = priority
            })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePicker(onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val mYear = calendar.get(Calendar.YEAR)
    val mMonth = calendar.get(Calendar.MONTH)
    val mDay = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.set(mYear, mMonth, mDay)
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = calendar.timeInMillis
    )
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(calendar.timeInMillis) }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    selectedDate = datePickerState.selectedDateMillis!!
                    val formattedDate = SimpleDateFormat("dd-MMM-yyyy").format(Date(selectedDate))
                    onDateSelected(formattedDate)
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = { showDatePicker = true },
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp))
                .padding(8.dp)
                .size(width = 90.dp, height = 30.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date Icon",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    text = "Date",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}

//        Text(
//            text = " Date: ${SimpleDateFormat("dd-MMM-yyyy").format(Date(selectedDate))}"
//        )


@SuppressLint("SuspiciousIndentation")
@Composable
fun MyPriority(onPriSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val contextForToast = LocalContext.current.applicationContext

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
        ){
        IconButton(onClick = {
            expanded = true },
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp))
                .padding(8.dp)
                .size(width = 90.dp, height = 30.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Flag,
                    contentDescription = "Open priority",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    text = "Priority",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier.background(Color.White)
        ) {
            DropdownMenuItem(
                text = { Text("Priority 1") },
                onClick = {
                    Toast.makeText(contextForToast, "Priority 1", Toast.LENGTH_SHORT).show()
                    expanded = false
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Flag,contentDescription = null,tint = Color(0xFFFC3462))
                }
            )
            DropdownMenuItem(
                text = { Text("Priority 2") },
                onClick = {
                    Toast.makeText(contextForToast, "Priority 2", Toast.LENGTH_SHORT).show()
                    expanded = false
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Flag,contentDescription = null,tint = Color(0xFFFA963F))
                }
            )
            DropdownMenuItem(
                text = { Text("Priority 3") },
                onClick = {
                    Toast.makeText(contextForToast, "Priority 3", Toast.LENGTH_SHORT).show()
                    expanded = false
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Flag,contentDescription = null,tint = Color(0xFFF3F159))
                }
            )
            DropdownMenuItem(
                text = { Text("Priority 4") },
                onClick = {
                    Toast.makeText(contextForToast, "Priority 4", Toast.LENGTH_SHORT).show()
                    expanded = false
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Flag,contentDescription = null,tint = Color(0xFF8FF359))
                }
            )
            DropdownMenuItem(
                text = { Text("Priority 5") },
                onClick = {
                    Toast.makeText(contextForToast, "Priority 5", Toast.LENGTH_SHORT).show()
                    expanded = false
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Flag,contentDescription = null)
                }
            )
        }
        }
}
