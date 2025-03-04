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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.o9tech.mytestafertoneday.R
import com.o9tech.mytestafertoneday.mainViewModel.ConfirmOrderViewModel
import com.o9tech.mytestafertoneday.ui.theme.appcolr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmScreen(
    navController: NavHostController,
    confirmOrderViewModel: ConfirmOrderViewModel,
    userAdress: String,
    selectedIndex: Int
) {
    var text by remember { mutableStateOf("") }
    var selectedPaymentMethod by remember { mutableStateOf<String?>(null) }
    val selectedIndexState = remember { mutableStateOf(selectedIndex) }

    var isChecked by remember { mutableStateOf(true) }
    var isCheckedw by remember { mutableStateOf(false) }

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    savedStateHandle?.getLiveData<String>("paymentMethod")?.observe(LocalLifecycleOwner.current) { method ->
        selectedPaymentMethod = method // Update the state with the selected option
    }

    val vehicleData = confirmOrderViewModel.vehicleData.collectAsState()
    val washPointData = confirmOrderViewModel.washOptions.collectAsState()

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text("Confirm Order")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack,
                            tint = appcolr,
                            contentDescription = "Menu Icon")
                    }
                },

                )
        },
        containerColor = Color.White,
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize().background(color = Color.White)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(text = "Location")
                        Card(
                            modifier = Modifier
                                .fillMaxWidth().height(50.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    painter =  painterResource(id = com.o9tech.mytestafertoneday.R.drawable.check_circle),
                                    contentDescription = "Menu Icon",
                                    tint = appcolr
                                )
                                TextField(
                                    value = userAdress,
                                    enabled = false,
                                    onValueChange = {  },
                                    placeholder = { Text(text = "Enter text here", fontSize = 14.sp, color = Color.Gray) },
                                    singleLine = true,
                                    colors = TextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        focusedContainerColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent,
                                        disabledTextColor = Color.Black,
                                        disabledPlaceholderColor = Color.Black
                                    ),
                                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = Color.Black)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Select car type", fontSize = 14.sp, fontWeight = FontWeight.W400)
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            vehicleData.value?.hydraMember?.let { it1 ->
                                items(it1.size) { index ->
                                    val isSelected = selectedIndexState.value == index

                                    Column(
                                        modifier = Modifier.padding(horizontal = 5.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Card(
                                            modifier = Modifier
                                                .height(100.dp)
                                                .width(100.dp)
                                                .clickable {
                                                    selectedIndexState.value = index

                                                },
                                            elevation = CardDefaults.cardElevation(4.dp),
                                            shape = RoundedCornerShape(8.dp),
                                            colors =   CardDefaults.cardColors(
                                            containerColor = if (isSelected) appcolr else Color.White)
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(8.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.pathimg),
                                                    contentDescription = "Vehicle Image",
                                                    colorFilter = if (isSelected) ColorFilter.tint(Color.White) else null,
                                                    modifier = Modifier
                                                        .size(50.dp)
                                                        .align(Alignment.CenterHorizontally)
                                                )
                                                Spacer(modifier = Modifier.height(8.dp))
                                                Text(
                                                    text = it1[index].name,
                                                    style = MaterialTheme.typography.bodyMedium.copy(
                                                        color = if (isSelected) Color.White
                                                        else MaterialTheme.colorScheme.onSurface
                                                    ),
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(
                                            text = it1[index].basePrice.formattedPrice,
                                            textAlign = TextAlign.Center
                                        )

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Car number")
                        Spacer(modifier = Modifier.height(6.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth().height(50.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    painter =  painterResource(id = com.o9tech.mytestafertoneday.R.drawable.check_circle),
                                    contentDescription = "Menu Icon",
                                    tint = appcolr
                                )
                                TextField(
                                    value = text,
                                    onValueChange = { text = it },
                                    placeholder = { Text(text = "Enter text here", fontSize = 14.sp, color = Color.Gray) },
                                    singleLine = true,
                                    colors = TextFieldDefaults.colors(
                                        unfocusedContainerColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        focusedContainerColor = Color.Transparent,
                                    ),
                                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = Color.Black)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "Wash point")
                        Spacer(modifier = Modifier.height(6.dp))

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ){
                                    Checkbox(
                                        checked = isChecked,
//                                        onCheckedChange = { isChecked = it },
                                        onCheckedChange = {  },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = appcolr,
                                            uncheckedColor = Color.Gray,
                                            checkmarkColor = Color.White
                                        )
                                    )
                                    Text(text = washPointData.value?.hydramem?.get(1)?.name.toString())
                                }
                                Text(text = washPointData.value?.hydramem?.get(1)?.price?.formattedPrice.toString())
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ){
                                    Checkbox(
                                        checked = isCheckedw,
                                        onCheckedChange = { isCheckedw = it },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = appcolr,
                                            uncheckedColor = Color.Gray,
                                            checkmarkColor = Color.White
                                        )
                                    )
                                    Text(text = washPointData.value?.hydramem?.get(0)?.name.toString())
                                }
                                Text(text = washPointData.value?.hydramem?.get(0)?.price?.formattedPrice.toString())
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))

                        Text(text = "Payment option")
                        Spacer(modifier = Modifier.height(6.dp))

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp).clickable {
                                    navController.navigate("AddPaymentMethod")
                                },
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween // Ensures items are spaced out
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    when (selectedPaymentMethod) {
                                        "card" -> Image(
                                            painter = painterResource(id = R.drawable.debitcard),
                                            contentDescription = "Card",
                                        )
                                        "cash" -> Image(
                                            painter = painterResource(id = R.drawable.groupimage),
                                            contentDescription = "Cash"
                                        )
                                        else -> Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "add"
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = selectedPaymentMethod ?: "Add payment method",
                                    )
                                }

                                if (selectedPaymentMethod == "cash" || selectedPaymentMethod == "card") {
                                    RadioButton(
                                        selected = true,
                                        onClick = { /* Optional: Handle radio button click */ },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = appcolr,
                                            unselectedColor = Color.Gray
                                        )
                                    )
                                }

                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(text = "Exterior")
                            Text(text = "PKR-1000")
                        }

                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(text = "Exterior")
                            Text(text = "PKR-1000")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
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
                                navController.navigate("FindWasherScreen")
                            }
                        ) {
                            Text(text = "Confirm order")
                        }
                    }

            }
        }
    )
}


//
//@Composable
//fun VehicleTypeSelection(
//    vehicalTypeConfirm: List<VehicleType>, // Replace with your actual data class
//    selectedIndex: Int,
//    onVehicleSelected: (Int, String) -> Unit // Callback for when a vehicle is selected
//) {
//    val selectedIndexState = remember { mutableStateOf(selectedIndex) }
//
//    LazyRow(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(vehicalTypeConfirm.size) { index ->
//            val isSelected = selectedIndexState.value == index
//
//            Column(
//                modifier = Modifier.padding(horizontal = 5.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Card(
//                    modifier = Modifier
//                        .height(120.dp)
//                        .width(100.dp)
//                        .clickable {
//                            selectedIndexState.value = index
//                            onVehicleSelected(
//                                index,
//                                vehicalTypeConfirm[index].id
//                            ) // Pass selected index and ID
//                        },
//                    elevation = 5.dp,
//                    shape = RoundedCornerShape(8.dp),
//                    backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary
//                    else MaterialTheme.colorScheme.surface
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(8.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        // Image
//                        Image(
//                            painter = painterResource(id = vehicalTypeConfirm[index].imageRes),
//                            contentDescription = "Vehicle Image",
//                            colorFilter = if (isSelected) ColorFilter.tint(Color.White) else null,
//                            modifier = Modifier
//                                .size(50.dp)
//                                .align(Alignment.CenterHorizontally)
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        // Text
//                        Text(
//                            text = vehicalTypeConfirm[index].name,
//                            style = MaterialTheme.typography.bodyMedium.copy(
//                                color = if (isSelected) Color.White
//                                else MaterialTheme.colorScheme.onSurface
//                            ),
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                }
//                Spacer(modifier = Modifier.height(5.dp))
//                Text(
//                    text = vehicalTypeConfirm[index].basePriceFormatted ?: "N/A",
//                    style = MaterialTheme.typography.bodySmall.copy(
//                        color = MaterialTheme.colorScheme.onSurfaceVariant
//                    ),
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//    }
//}
