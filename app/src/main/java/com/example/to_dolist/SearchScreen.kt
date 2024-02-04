package com.example.to_dolist


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(){
    var expanded by remember{ mutableStateOf(false) }
    TopAppBar(

        title = {
            Text(
                text = "Search",
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        actions ={
//            ปุ่มค้นหาด้านบน
//            IconButton(
//                onClick = {
//                }) {
//                Icon(imageVector = Icons.Outlined.Search
//                    ,contentDescription = "Search")
//            }

            IconButton(
                onClick = { expanded=true
                }) {
                Icon(Icons.Default.MoreVert,contentDescription = "Open Menu")
            }
//            dropdown
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded= false  }
            ){
//                menu
                DropdownMenuItem(
                    text = { Text("Setting")},
                    onClick = {

                        expanded= false
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.Settings,contentDescription = null)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Delete Task")},
                    onClick = {

                        expanded= false
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.DeleteOutline,contentDescription = null)
                    }
                )

                DropdownMenuItem(
                    text = { Text("Logout")},
                    onClick = {

                        expanded= false
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.Logout,contentDescription = null)
                    }
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(android.graphics.Color.parseColor("#99CCFF"))
        )
    )
}


@Composable
fun Search() {
    val navController = rememberNavController()
    val contextForToast = LocalContext.current
    var textFieldSearch by remember { mutableStateOf("") }

    Scaffold(
        topBar = { SearchTopAppBar() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp + paddingValues.calculateTopPadding()), // Use paddingValues
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    modifier = Modifier.width(350.dp),
                    value = textFieldSearch,
                    onValueChange = { textFieldSearch = it },
                    label = { Text(text = "Search")}
                )
            }
        }
    )
}



