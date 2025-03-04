package com.o9tech.mytestafertoneday.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.o9tech.mytestafertoneday.R
import com.o9tech.mytestafertoneday.mainViewModel.AddPaymentViewModel
import com.o9tech.mytestafertoneday.ui.theme.appcolr


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPaymentMethod(navController: NavHostController, addPaymentViewModel: AddPaymentViewModel) {

    var selectedOption by remember { mutableStateOf<String?>(null) } // No option selected by default

    val paymentMethods = addPaymentViewModel.payment.collectAsState()
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text("Add Payment Method") // Add your title here
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            tint = appcolr,
                            contentDescription = "Menu Icon"
                        )
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                        .padding(horizontal = 20.dp),
                ) {
                    Text(text = "Payment Methods")
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clickable {
                                selectedOption = "cash"
                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                    "paymentMethod",
                                    selectedOption
                                )
                                navController.popBackStack()
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp), // Adds space inside the card
                            verticalAlignment = Alignment.CenterVertically, // Aligns items in the center vertically
                            horizontalArrangement = Arrangement.SpaceBetween // Evenly distributes items
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.groupimage),
                                    contentDescription = "cash"
                                )
                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = paymentMethods.value?.hydramember?.get(0)?.title
                                        ?: "cash"
                                )
                            }
                            RadioButton(
                                selected = selectedOption == "cash",
                                onClick = { selectedOption = "cash" },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = appcolr,
                                    unselectedColor = Color.Gray
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp)
                                .clickable {
                                    selectedOption = "card"
                                    navController.previousBackStackEntry?.savedStateHandle?.set(
                                        "paymentMethod",
                                        selectedOption
                                    )
                                    navController.popBackStack()
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Image(
                                    painter = painterResource(id = R.drawable.debitcard),
                                    contentDescription = "card"
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = paymentMethods.value?.hydramember?.get(1)?.title
                                        ?: "card"
                                )
//                                Text(text = "card")
                            }
                            RadioButton(
                                selected = selectedOption == "card",
                                onClick = { selectedOption = "card" },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = appcolr,
                                    unselectedColor = Color.Gray
                                )
                            )
                        }
                    }
                }
            }
        }
    )
}


//@Composable
//fun RadioButtonExample() {
//    var selectedOption by remember { mutableStateOf("Option 1") }
//
//    Column(
//        modifier = Modifier.padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        Text(text = "Choose an Option:")
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.clickable { selectedOption = "Option 1" }
//        ) {
//            RadioButton(
//                selected = selectedOption == "Option 1",
//                onClick = { selectedOption = "Option 1" },
//                colors = RadioButtonDefaults.colors(
//                    selectedColor = Color.Blue,
//                    unselectedColor = Color.Gray
//                )
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(text = "Option 1")
//        }
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.clickable { selectedOption = "Option 2" }
//        ) {
//            RadioButton(
//                selected = selectedOption == "Option 2",
//                onClick = { selectedOption = "Option 2" },
//                colors = RadioButtonDefaults.colors(
//                    selectedColor = Color.Blue,
//                    unselectedColor = Color.Gray
//                )
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(text = "Option 2")
//        }
//
//        Text(
//            text = "Selected: $selectedOption",
//            modifier = Modifier.padding(top = 16.dp),
//            color = Color.DarkGray
//        )
//    }
//}
