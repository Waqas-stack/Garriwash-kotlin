package com.o9tech.mytestafertoneday.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.o9tech.mytestafertoneday.mainViewModel.WashpointViewModel
import com.o9tech.mytestafertoneday.ui.theme.appcolr

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun WashPointScreen(navController: NavHostController, washpointViewModel: WashpointViewModel) {

    val washpoint = washpointViewModel.waspoint.collectAsState()

    Scaffold (
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text("Wash Point")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            tint = appcolr,
                            contentDescription = "Menu Icon")
                    }
                },

                )
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(10.dp).background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    washpoint.value?.hydraa?.let { it1 ->
                        items(it1.size){
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically, // Align content vertically in the center
                                horizontalArrangement = Arrangement.Start // Align items to the start
                            ) {
                                Icon(
//                                    painter = painterResource(id = R.drawable.picture_frame),
                                    painter =  painterResource(id = com.o9tech.mytestafertoneday.R.drawable.person_pin),
                                    contentDescription = "Star Icon",
                                    tint = Color.Blue,
                                    modifier = Modifier.size(24.dp) // Icon size
                                )
                                Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
                                Text(
//                                    text = "Star Icon a quick brown fox jumps over the lazy dog and this is a test",
                                    text = it1[it].address,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}




