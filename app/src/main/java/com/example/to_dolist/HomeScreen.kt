package com.example.to_dolist

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.DeleteSweep
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.navigation.NavController

@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(){
    var expanded by remember{ mutableStateOf(false) }
    var select by remember{ mutableStateOf(false) }
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Task",
                    modifier = Modifier.padding(start = 16.dp)
                )

                IconButton(
                    onClick = { select = true }
                ) {
                    Icon(Icons.Default.ExpandMore, contentDescription = "Open select")
                }
                DropdownMenu(
                    expanded = select,
                    onDismissRequest = { select = false }
                ) {
//                menu
                    DropdownMenuItem(
                        text = { Text("All") },
                        onClick = {
                            select = false
                        },
                        leadingIcon = {
                            Icon(Icons.Outlined.Book, contentDescription = null)
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Favorit") },
                        onClick = {
                            select = false
                        },
                        leadingIcon = {
                            Icon(Icons.Outlined.Star, contentDescription = null)
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Last Delete") },
                        onClick = {
                            select = false
                        },
                        leadingIcon = {
                            Icon(Icons.Outlined.DeleteSweep, contentDescription = null)
                        }
                    )
                }
            }

},
        actions ={
// ปุ่มค้นหาด้านบน
            IconButton(
                onClick = {
                }) {
                Icon(imageVector = Icons.Outlined.Search
                    ,contentDescription = "Search")
            }
//3จุดไข่ปลา
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
//                        Toast.makeText(contextForToast,"Setting", Toast.LENGTH_SHORT)
//                            .show()
                        expanded= false
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.Settings,contentDescription = null)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Delete Task")},
                    onClick = {
//                        Toast.makeText(contextForToast,"Delete Task", Toast.LENGTH_SHORT)
//                            .show()
                        expanded= false
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.DeleteOutline,contentDescription = null)
                    }
                )

                DropdownMenuItem(
                    text = { Text("Logout")},
                    onClick = {
//                        Toast.makeText(contextForToast,"Logout", Toast.LENGTH_SHORT)
//                            .show()
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
fun HomeScreen() {
    val contextForToast = LocalContext.current
    val navController = rememberNavController()

    Scaffold(
        topBar = { HomeTopAppBar() },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            MyFloatingActionButton(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Home Sc")
        }
    }
}

@Composable
fun MyFloatingActionButton(navController: NavController) {
    FloatingActionButton(
        onClick = {
            if (navController.currentBackStackEntry?.destination?.route != Screen.Insert.route) {
                navController.navigate(Screen.Insert.route)
            } else {
                navController.popBackStack()
            }
        }
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "add icon")
    }
}
