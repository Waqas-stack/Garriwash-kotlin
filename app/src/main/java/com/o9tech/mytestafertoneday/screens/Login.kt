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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import com.o9tech.mytestafertoneday.data.model.loginmodels.LoginRequestModel
import com.o9tech.mytestafertoneday.mainViewModel.MainViewModel
import com.o9tech.mytestafertoneday.ui.theme.appcolr


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val login=mainViewModel.login.collectAsState()
    Scaffold(
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
                        modifier = Modifier.fillMaxSize(),
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
                                    Text(text = "Email/Phone Number")
                                },
                                value = username, onValueChange = {
                                    username = it
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

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

                        Text(
                            color = appcolr,
                            fontWeight = FontWeight.Bold,
                            text = "Forgot Password?",
                            modifier = Modifier
                                .padding(end = 40.dp, top = 20.dp)
                                .align(Alignment.End)
                                .clickable {
                                    navController.navigate("ForgotPasswordScreen")
                                }
                        )

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

                                mainViewModel.login(
                                    LoginRequestModel(
                                        username = "+92${username}",
                                        password = password
                                    )
                                )
                                login.value?.user?.id?.let {
                                    navController.navigate("MyHomeScreen")
                                }
//                                if (login.value?.user?.id!=null){
//                                    navController.navigate("MyHomeScreen")
//                                }

                            }
                        ) {
                            Text(text = "Login")
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
                        Text(text = "Don't have an account?")
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "Register now",
                            color = appcolr,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController.navigate("SignupScreen")
                            })
                    }
                }
            }
        }
    )
}








