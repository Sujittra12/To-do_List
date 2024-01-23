package com.example.to_dolist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (val route:String ,val name:String,val icon: ImageVector){
//    object ToDay: Screen(route = "ToDay", name = "ToDay" , icon = Icons.Default.Home)

//   ใส่ไอคอนตรงนี้

    object Home: Screen(route = "Home", name = "To Day" , icon = Icons.Default.CalendarToday)
    object Notic: Screen(route = "Notic", name = "Notic" , icon = Icons.Default.NotificationsActive)
    object Follow: Screen(route = "Follow", name = "Follow" , icon = Icons.Default.BarChart)
    object Profile: Screen(route = "Profile", name = "Profile" , icon = Icons.Default.Person)
}