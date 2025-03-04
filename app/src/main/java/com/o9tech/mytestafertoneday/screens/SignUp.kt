package com.o9tech.mytestafertoneday.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.o9tech.mytestafertoneday.R
import com.o9tech.mytestafertoneday.data.model.signupmodels.SignupRequestModel
import com.o9tech.mytestafertoneday.mainViewModel.MainViewModel
import com.o9tech.mytestafertoneday.ui.theme.appcolr

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SignupScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phonenum by remember { mutableStateOf("") }
    var idcard by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val signup=mainViewModel.signup.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text("") // Add your title here
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Menu Icon")
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
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.login_img),
                            contentDescription = "login image",
                            modifier = Modifier.size(140.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Person",
                                tint = appcolr
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            TextField(
                                modifier = Modifier.width(300.dp),
                                singleLine = true,
                                maxLines = 1,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = appcolr,
                                    unfocusedIndicatorColor = Color.Gray,
                                    focusedContainerColor = Color.Transparent,
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                placeholder = {
                                    Text(text = "User Name")
                                },
                                value = username, onValueChange = {
                                    username = it
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = appcolr
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            TextField(
                                modifier = Modifier.width(300.dp),
                                singleLine = true,
                                maxLines = 1,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = appcolr,
                                    unfocusedIndicatorColor = Color.Gray,
                                    focusedContainerColor = Color.Transparent,
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                placeholder = {
                                    Text(text = "Email")
                                },
                                value = email, onValueChange = {
                                    email = it
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "phone number",
                                tint = appcolr
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            TextField(
                                modifier = Modifier.width(300.dp),
                                singleLine = true,
                                maxLines = 1,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = appcolr,
                                    unfocusedIndicatorColor = Color.Gray,
                                    focusedContainerColor = Color.Transparent,
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                placeholder = {
                                    Text(text = "Phone Number")
                                },
                                value = phonenum, onValueChange = {
                                    phonenum = it
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = "national id",
                                tint = appcolr
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            TextField(
                                modifier = Modifier.width(300.dp),
                                singleLine = true,
                                maxLines = 1,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = appcolr,
                                    unfocusedIndicatorColor = Color.Gray,
                                    focusedContainerColor = Color.Transparent,
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                placeholder = {
                                    Text(text = "National ID")
                                },
                                value = idcard, onValueChange = {
                                    idcard = it
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))





                        ///////--------------------------------------------------------------------------------
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                tint = appcolr,
                                contentDescription = "password"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            TextField(
                                modifier = Modifier.width(300.dp),
                                maxLines = 1,
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = appcolr,
                                    unfocusedIndicatorColor = Color.Gray,
                                    focusedContainerColor = Color.Transparent,
                                ),
                                trailingIcon = {

                                    IconButton(onClick = {
                                        isPasswordVisible = !isPasswordVisible
                                    }) {
                                        val passwordIcon = if (isPasswordVisible) {
                                            painterResource(id = R.drawable.baseline_visibility_24)  // replace with your actual icon resource
                                        } else {
                                            painterResource(id = R.drawable.baseline_visibility_off_24)  // replace with your actual icon resource
                                        }

                                        Icon(
                                            painter = passwordIcon,
                                            tint = appcolr,
                                            contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password"
                                        )
                                    }

                                },
                                visualTransformation = if (isPasswordVisible) VisualTransformation.None
                                else PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                placeholder = {
                                    Text(text = "Password")
                                },
                                value = password, onValueChange = {
                                    password = it
                                }
                            )
                        }

//                        Text(
//                            color = appcolr,
//                            fontWeight = FontWeight.Bold,
//                            text = "Forgot Password?",
//                            modifier = Modifier
//                                .padding(end = 40.dp, top = 20.dp)
//                                .align(Alignment.End)
//                                .clickable { }
//                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = appcolr,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 36.dp)
                                .height(50.dp),
                            onClick = {
                                mainViewModel.signup(SignupRequestModel(
                                    fullName = username,
                                    email = email,
                                    phone = phonenum,
                                    nationalIdentityNumber = idcard,
                                    password = password
                                ))
                            }
                        ) {
                            Text(text = "Sign Up")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Already have an account?")
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "Sign In",
                            color = appcolr,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            })
                    }
                }
            }
        }
    )
}