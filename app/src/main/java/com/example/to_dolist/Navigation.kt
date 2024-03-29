package com.example.to_dolist

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
//        ค่าเริ่มต้นหน้าเเรกตอนเปิดเเอป
        startDestination =Screen.Home.route
    ){
        composable(
            route =Screen.Home.route
        ){
            HomeScreen()
        }

        composable(
            route = Screen.Insert.route
        ){
            InsertScreen(navController)
        }

        composable(
            route =Screen.Notic.route
        ){
            NoticScreen()
        }

        composable(
            route =Screen.Follow.route
        ){
            FollowScreen()
        }

        composable(
            route =Screen.Profile.route
        ){
            ProfileScreen()
        }


        
        
        
    }

}