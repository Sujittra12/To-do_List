package com.example.to_dolist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.to_dolist.ui.theme.TodoListTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//ของเรา
//เเทบบาร์ด้านบน
@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(contextForToast: Context){
    var expanded by remember{ mutableStateOf(false) }
    CenterAlignedTopAppBar(
        title = {
            Text(text="To-do list")
        },
        actions ={
            IconButton(
                onClick = { Toast.makeText(contextForToast,"Notifications", Toast.LENGTH_SHORT)
                    .show()
                }) {
                Icon(imageVector = Icons.Outlined.NotificationsNone
                    ,contentDescription = "Notifications")
            }
            IconButton(
                onClick = { Toast.makeText(contextForToast,"Home", Toast.LENGTH_SHORT)
                    .show()
                }) {
                Icon(imageVector = Icons.Outlined.Home
                    ,contentDescription = "Home")
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
                        Toast.makeText(contextForToast,"Setting", Toast.LENGTH_SHORT)
                            .show()
                        expanded= false
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.Settings,contentDescription = null)
                    }
                )

                DropdownMenuItem(
                    text = { Text("Logout")},
                    onClick = {
                        Toast.makeText(contextForToast,"Logout", Toast.LENGTH_SHORT)
                            .show()
                        expanded= false
                    },
                    leadingIcon = {
                        Icon(Icons.Outlined.Logout,contentDescription = null)
                    }
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Blue.copy(alpha = 0.3f)
        )
    )
}

//เเทบเมนูด้านล่าง
@Composable
fun MyButtomBar(navController: NavHostController, contextForToast: Context){
    val navigationItem = listOf(
        Screen.Home,
        Screen.Profile,
        Screen.Follow,
        Screen.Notic
    )
    var selectedScreen by remember {
        mutableIntStateOf(0)
    }
    NavigationBar {
        navigationItem.forEachIndexed{index,screen->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon,contentDescription = null)},
                label = { Text(text = screen.name)},
                selected = (selectedScreen == index),
                onClick = {
                    if (navController.currentBackStack.value.size >=2){
                        navController.popBackStack()
                    }
                    selectedScreen = index
                    navController.navigate(screen.route)
                    Toast.makeText(contextForToast,screen.name,Toast.LENGTH_SHORT).show()


                },
            )
        }
    }
}

//หน้าจอเเสดงผล

@Composable
fun MyScaffoldLayout(){
    val contextForToast = LocalContext.current.applicationContext
    val navController = rememberNavController()
    Scaffold (
        topBar ={ MyTopAppBar(contextForToast = contextForToast)},
        bottomBar ={ MyButtomBar(navController , contextForToast  )},
        floatingActionButtonPosition = FabPosition.End,
    ){
            paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Text(text = "Screen area")
        }
        NavGraph(navController = navController)
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListTheme {
        Greeting("Android")
    }
}

@Composable
fun LoginActivity(username: String, onUserNameChange: (String)-> Unit,
                   password: String, onPasswordChange: (String)-> Unit){
Column (modifier = Modifier.padding(20.dp)){
    TextField(
        value = username,
        onValueChange = { onUserNameChange(it) },
        label = { Text("Username") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )

    TextField(
        value = password,
        onValueChange = { onPasswordChange(it) },
        label = { Text("Password") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        visualTransformation = PasswordVisualTransformation()
    )

}
}

@Composable
fun LoginScreen(){
    var textInformation by rememberSaveable { mutableStateOf("") }

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var forgetpassword by rememberSaveable { mutableStateOf("") }
    var register by rememberSaveable { mutableStateOf("") }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Login",
            modifier = Modifier.padding(top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp)

        LoginActivity(
            username = username,
            onUserNameChange = {username=it},
            password = password,
            onPasswordChange = {password=it})

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = {
                // Handle Forget Password click
            }) {
                Text(
                    text = "Forget Password",
                    modifier = Modifier.padding(start = 0.dp),
                    fontSize = 13.sp,
                    color = Color.Red
                )
            }

            TextButton(onClick = {
                // Handle Register click
            }) {
                Text(
                    text = "Register",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 13.sp,
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    textInformation = "Name: $username"
                },
                modifier = Modifier.width(140.dp)
                    .padding(top = 0.dp)
            ) {
                Text(text = "Login")
            }
        }


    }
}


