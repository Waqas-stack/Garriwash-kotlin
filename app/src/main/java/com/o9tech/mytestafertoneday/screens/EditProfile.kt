package com.o9tech.mytestafertoneday.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.o9tech.mytestafertoneday.R
import com.o9tech.mytestafertoneday.data.model.UpdateUserModelRequest.UpdateUserModelisRequest
import com.o9tech.mytestafertoneday.screens.maps.MapViewModel
import com.o9tech.mytestafertoneday.ui.theme.appcolr


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun EditProfile(navController: NavHostController, mapViewModel: MapViewModel) {
    val userdata = mapViewModel.userData.collectAsState()

    var name by remember { mutableStateOf(userdata.value?.fullName) }
    var email by remember { mutableStateOf(userdata.value?.email) }
    var number by remember { mutableStateOf(userdata.value?.phone) }
    var idcard by remember { mutableStateOf(userdata.value?.nationalIdentityNumber) }
    var password by remember { mutableStateOf(userdata.value?.phone) }
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text("Edit profile")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            tint = appcolr,
                            contentDescription = "ArrowBack"
                        )
                    }
                },

                )
        },
        content = {
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(color = Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(120.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(color = appcolr, shape = CircleShape)
                        )

                        Box(
                            modifier = Modifier
                                .size(110.dp)
                                .background(color = Color.White, shape = CircleShape)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.washtracking), // Replace with your image
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Upload Icon",
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(
                                    x = 1.dp,
                                    y = (-10).dp
                                )
                                .background(
                                    color = appcolr,
                                    shape = CircleShape
                                )
                                .padding(8.dp)
                                .size(12.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = userdata.value?.fullName.toString(),fontSize = 26.sp, fontWeight = FontWeight.W400)
                    Spacer(modifier = Modifier.height(8.dp))
                    name?.let { it1 ->
                        TextField(
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = appcolr,
                                unfocusedIndicatorColor = Color.Gray,
                                focusedContainerColor = Color.Transparent,
                            ),
                            label = {
                                Text(text = "Name",color = appcolr)
                            },
                            placeholder = {
                                Text(text = "Enter Name")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            value = it1, onValueChange = {
                                name = it
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    email?.let { it1 ->
                        TextField(
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = appcolr,
                                unfocusedIndicatorColor = Color.Gray,
                                focusedContainerColor = Color.Transparent,
                            ),
                            label = {
                                Text(text = "Email",color = appcolr)
                            },
                            placeholder = {
                                Text(text = "Enter Email")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            value = it1, onValueChange = {
                                email=it
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    number?.let { it1 ->
                        TextField(
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = appcolr,
                                unfocusedIndicatorColor = Color.Gray,
                                focusedContainerColor = Color.Transparent,
                            ),
                            label = {
                                Text(text = "Number",color = appcolr)
                            },
                            placeholder = {
                                Text(text = "Enter Number")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            value = it1, onValueChange = {
                                number=it
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    idcard?.let { it1 ->
                        TextField(
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = appcolr,
                                unfocusedIndicatorColor = Color.Gray,
                                focusedContainerColor = Color.Transparent,
                            ),
                            label = {
                                Text(text = "Id Card",color = appcolr)
                            },
                            placeholder = {
                                Text(text = "Enter Id Card")
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            value = it1, onValueChange = {
                                idcard=it
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    password?.let { it1 ->
                        TextField(
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = appcolr,
                                unfocusedIndicatorColor = Color.Gray,
                                focusedContainerColor = Color.Transparent,
                            ),
                            placeholder = {
                                Text(text = "Enter Password")
                            },
                            label = {
                                Text(text = "Password",color = appcolr)
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            value = it1, onValueChange = {
                                password=it
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = appcolr,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {
                            mapViewModel.updateUserData(
                                UpdateUserModelisRequest(
                                    fullName = name ?: "",
                                    latitude = userdata.value?.latitude ?: 0.0,
                                    longitude = userdata.value?.longitude ?: 0.0,
                                    email = email ?: "",
                                    phone = number ?: "",
                                    nationalIdentityNumber = idcard ?: "",
                                    password = password ?: "")

                            )
                        }
                    ) {
                        Text(text = "Save")
                    }

                }
            }
        }
    )
}

