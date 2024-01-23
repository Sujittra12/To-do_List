package com.example.to_dolist

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


@Composable
fun ProfileScreen(){
    val contextForToast= LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text ="Profile Screen"
        )

//        Button(
//            onClick = {
//                Toast.makeText(contextForToast,"This is Profile Screen", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        ){
//            Text("Click")
//        }


    }

}