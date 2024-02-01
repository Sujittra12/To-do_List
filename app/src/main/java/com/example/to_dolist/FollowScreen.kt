package com.example.to_dolist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController


@Composable
fun FollowScreen() {
    var all = 10
    var finish = 2
    var late = 6
    var doing = 2
    var day = 3
    val pList = listOf("project Mobile", "project Security", "project DataEngineer")
    val contextForToast = LocalContext.current
    val navController = rememberNavController()
    Scaffold(
        topBar = { FollowTopAppBar() },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Overview",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(
                        start = 30.dp,
                        top = 30.dp,
                        bottom = 10.dp
                    )
                )
            }
            Row(
                modifier = Modifier.padding(start = 30.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .width(160.dp)
                            .height(165.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(android.graphics.Color.parseColor("#409FCA"))
                                .copy(alpha = 0.3f)
                        ),
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "${all}", fontSize = 40.sp)
                                Text(
                                    text = "All to do list",
                                    modifier = Modifier
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Row {
                        Card(
                            modifier = Modifier
                                .padding(start = 9.dp, bottom = 10.dp)
                                .width(95.dp)
                                .height(77.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(android.graphics.Color.parseColor("#409FCA"))
                                    .copy(alpha = 0.3f)
                            ),
                            content = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "${finish}",
                                        fontSize = 24.sp,
                                        color = Color(52, 160, 69)
                                    )
                                    Text(text = "completed", fontSize = 12.sp)
                                }
                            }
                        )

                        Card(
                            modifier = Modifier
                                .padding(start = 9.dp, bottom = 10.dp)
                                .width(95.dp)
                                .height(77.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(android.graphics.Color.parseColor("#409FCA"))
                                    .copy(alpha = 0.3f)
                            ),
                            content = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "${late}",
                                        fontSize = 24.sp,
                                        color = Color(212, 38, 27)
                                    )
                                    Text(text = "late", fontSize = 15.sp)
                                }
                            }
                        )
                    }
                    Row {
                        Card(
                            modifier = Modifier
                                .padding(start = 9.dp)
                                .width(200.dp)
                                .height(78.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(android.graphics.Color.parseColor("#409FCA"))
                                    .copy(alpha = 0.3f)
                            ),
                            content = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "${doing}", fontSize = 22.sp)
                                    Text(text = "doing", fontSize = 15.sp)
                                }
                            }
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Next to do ( ${day} day)",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(
                        start = 30.dp,
                        top = 10.dp,
                        bottom = 5.dp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp)
                    .fillMaxWidth()
            ) {
                pList.forEachIndexed { index, item ->
                    Row(
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 7.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Icon(
                                Icons.Outlined.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 15.dp, end = 5.dp)
                                    .size(24.dp)
                            )
                            Text(text = item, fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}




@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowTopAppBar(){
    var expanded by remember{ mutableStateOf(false) }
    TopAppBar(

        title = {
            Text(
                text = "Follow",
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
