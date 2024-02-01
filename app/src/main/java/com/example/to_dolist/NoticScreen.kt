package com.example.to_dolist

import android.content.Context
//import android.graphics.Color
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.compose.rememberNavController



@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticTopAppBar(){
    var expanded by remember{ mutableStateOf(false) }
    TopAppBar(

        title = {
            Text(
                text = "Notification",
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        actions ={
//            ปุ่มค้นหาด้านบน
            IconButton(
                onClick = {
                }) {
                Icon(imageVector = Icons.Outlined.Search
                    ,contentDescription = "Search")
            }

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
fun NoticScreen(){
    val contextForToast= LocalContext.current
    val navController = rememberNavController()
    Scaffold (
        topBar ={ NoticTopAppBar()},
    ){
            paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text ="Notic"
            )
        }
    }
}



