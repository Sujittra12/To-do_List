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
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.UploadFile
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
    var insertItemsList = remember { mutableStateListOf<Inserted>() }
    var textFieldTitle by remember { mutableStateOf("") }
    var textFieldDesc by remember { mutableStateOf("") }
    var selectedDate by rememberSaveable { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var selectedFile by remember { mutableStateOf("") }

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
            selectedDate = ""
            selectedPriority = ""
            selectedCategory = ""
            if (navController.currentBackStack.value.size >= 2) {
                navController.navigate(Screen.Home.route)
            }
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }

        IconButton(onClick = {
            // ทำงานที่ต้องการเมื่อคลิกที่ปุ่ม Confirm
            insertItemsList.add(
                Inserted(
                    textFieldTitle,
                    textFieldDesc,
                    selectedDate,
                    selectedPriority,
                    selectedCategory,
                    selectedFile
                )
            )

            // ล้างค่าตัวแปร
            textFieldTitle = ""
            textFieldDesc = ""
            selectedDate = ""
            selectedPriority = ""
            selectedCategory = ""

            if (navController.currentBackStack.value.size >= 2) {
                navController.navigate(Screen.Home.route)
            }
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

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = textFieldTitle,
            onValueChange = { textFieldTitle = it },
            placeholder = { Text(text = "Title",style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
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

            Spacer(modifier = Modifier.height(10.dp))

            MyPriority(onPriSelected = { priority ->
                selectedPriority = priority
            })

            Spacer(modifier = Modifier.height(10.dp))

            MyCategory(onCatSelected = {category ->
                selectedCategory = category
            })

            Spacer(modifier = Modifier.height(10.dp))

            MyFile(onFileSelected = {file ->
                selectedFile = file
            })
        }
    }

}

//เลือกวันที่
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
            .padding(start = 8.dp)
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
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 15.sp
                )
            }

        }
        Text(
            text = "Date: ${SimpleDateFormat("dd-MMM-yyyy").format(Date(selectedDate))}",
            modifier = Modifier.padding(10.dp)
        )

    }

}





//เลือก Priority
@SuppressLint("SuspiciousIndentation")
@Composable
fun MyPriority(onPriSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedPriority by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
    ){
        IconButton(onClick = {
            expanded = true
        },
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
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 15.sp
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
                    selectedPriority = "Priority 1"
                    onPriSelected("Priority 1")
                    expanded = false
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Flag, contentDescription = null, tint = Color(0xFFFC3462))
                }
            )
            DropdownMenuItem(
                text = { Text("Priority 2") },
                onClick = {
                    selectedPriority = "Priority 2"
                    onPriSelected("Priority 2")
                    expanded = false
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Flag, contentDescription = null, tint = Color(0xFFFA963F))
                }
            )
            DropdownMenuItem(
                text = { Text("Priority 3") },
                onClick = {
                    selectedPriority = "Priority 3"
                    onPriSelected("Priority 3")
                    expanded = false
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Flag, contentDescription = null, tint = Color(0xFFF3F159))
                }
            )
            DropdownMenuItem(
                text = { Text("Priority 4") },
                onClick = {
                    selectedPriority = "Priority 4"
                    onPriSelected("Priority 4")
                    expanded = false
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Flag, contentDescription = null, tint = Color(0xFF8FF359))
                }
            )
            DropdownMenuItem(
                text = { Text("Priority 5") },
                onClick = {
                    selectedPriority = "Priority 5"
                    onPriSelected("Priority 5")
                    expanded = false
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Flag, contentDescription = null)
                }
            )
        }
        Text(
            text = "Priority: $selectedPriority",
            modifier = Modifier.padding(10.dp)
        )
    }
}

//เลือก category
@Composable
fun MyCategory(onCatSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val contextForToast = LocalContext.current.applicationContext
    var selectedCategory by remember { mutableStateOf("") }
    var newCategory by remember { mutableStateOf("") }
    var categories = listOf(
        "Category 1",
        "Category 2",
        "Category 3",
        // เพิ่มต่อไปตามลำดับ
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
    ) {
        IconButton(
            onClick = { expanded = true },
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
                    imageVector = Icons.Default.Category,
                    contentDescription = "Open category",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    text = "Category",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 13.sp
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
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category) },
                    onClick = {
                        expanded = false
                        selectedCategory = category
                        onCatSelected(category)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

            // ตัวเลือกเพิ่ม Category ใหม่
            TextField(
                value = newCategory,
                onValueChange = { newCategory = it },
                label = { Text("New Category") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            // ปุ่มเพิ่ม Category ใหม่
            Button(
                onClick = {
                    if (newCategory.isNotBlank()) {
                        Toast.makeText(contextForToast, "Added: $newCategory", Toast.LENGTH_SHORT).show()
                        expanded = false
                        selectedCategory = newCategory
                        onCatSelected(newCategory)
                        newCategory = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Add")
            }
        }

        // แสดง Category
        Text(
            text = "Category: $selectedCategory",
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun MyFile(onFileSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedFile by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
    ) {
        IconButton(
            onClick = {
                expanded = true
            },
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
                    imageVector = Icons.Default.UploadFile,
                    contentDescription = "Open File",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    text = "File",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 15.sp
                )
            }
        }
        Text(
            text = "File: ",
            modifier = Modifier.padding(10.dp)
        )
    }
}