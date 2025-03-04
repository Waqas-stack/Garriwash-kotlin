//package com.o9tech.mytestafertoneday.screens
//
//import android.R
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.pm.PackageManager
//import android.util.Log
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AccountCircle
//import androidx.compose.material.icons.filled.Check
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Divider
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.Icon
//import androidx.compose.material3.LocalTextStyle
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.ModalNavigationDrawer
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.material3.rememberDrawerState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.core.content.ContextCompat
//import androidx.navigation.NavHostController
//import com.google.android.gms.maps.model.CameraPosition
//import com.google.android.gms.maps.model.LatLng
//import com.google.maps.android.compose.GoogleMap
//import com.google.maps.android.compose.Marker
//import com.google.maps.android.compose.MarkerState
//import com.google.maps.android.compose.rememberCameraPositionState
//
//import com.o9tech.mytestafertoneday.ui.theme.appcolr
//import kotlinx.coroutines.launch
//
//
////@Preview(showBackground = true)
//@SuppressLint("UnrememberedMutableState")
//@Composable
//fun MyHomeScreen(navController: NavHostController, homeViewModel: HomeViewModel,activity: Activity) {
//
//
//
//    var text by remember { mutableStateOf("") }
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
////    val loactions = homeViewModel.location.()
////    val loactions = homeViewModel.location.collectAsState().value
//    val loactions by homeViewModel.location.collectAsState()
//
//
//
//    val currentLocation = LatLng(31.4312, 74.2644) // Example: San Francisco
//    val cameraPositionState = rememberCameraPositionState {
////        position = CameraPosition.fromLatLngZoom(currentLocation, 14f)
//        loactions?.let {
////            position = CameraPosition.fromLatLngZoom(it, 15f)
//            position = CameraPosition.fromLatLngZoom(it, 14f)
//
//        }
//    }
//
//    LaunchedEffect(Unit) {
//        homeViewModel.getLocation()
//        if (loactions == null) {
//            Log.d("Loggis", "Location is null!")
//        } else {
//            Log.d("Loggis", "Location: Latitude: ${loactions?.latitude}, Longitude: ${loactions?.longitude}")
//        }
//    }
//
//
////    LaunchedEffect(loactions) {
////
////    }
////
////    LaunchedEffect(Unit) {
////
////
////        if (ContextCompat.checkSelfPermission(
////                activity,
////                android.Manifest.permission.ACCESS_FINE_LOCATION
////            ) != PackageManager.PERMISSION_GRANTED
////        ) {
////            homeViewModel.requestLocationPermission(activity)
////        } else {
////            homeViewModel.getLocation()
////            Log.d("Loggis", "inscreen: ${loactions?.latitude} ${loactions?.longitude}")
////        }
////    }
//    val vehicledata = homeViewModel.vehicleData.collectAsState()
//
//
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            Column(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .width(250.dp)
//                    .background(MaterialTheme.colorScheme.surface)
//                    .clip(
//                        RoundedCornerShape(
//                            topStart = 16.dp,
//                            topEnd = 16.dp,
//                            bottomStart = 16.dp,
//                            bottomEnd = 16.dp
//                        )
//                    )
//                    .padding(16.dp)
//            ) {
//
//                Column(
//                    modifier = Modifier.background(color = Color.Blue),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    // Profile Image - Circular
//                    Image(
//                        painter = painterResource(id = R.drawable.ic_menu_gallery), // Replace with your image resource
//                        contentDescription = "Profile Image",
//                        modifier = Modifier
//                            .size(80.dp)
//                            .clip(CircleShape) // Circle shape
//                            .border(2.dp, Color.White, CircleShape) // White border around the image
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    // User Info - Name and Phone number
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(
//                            text = "User Name", // Replace with dynamic name
//                            style = MaterialTheme.typography.headlineMedium,
//                            color = Color.White
//                        )
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = "+123 456 7890", // Replace with dynamic phone number
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = Color.White
//                        )
//                    }
//                }
//                // Header
////                Row(
////                    verticalAlignment = Alignment.CenterVertically,
////                    modifier = Modifier.padding(bottom = 16.dp)
////                ) {
////                    Icon(
////                        imageVector = Icons.Default.AccountCircle,
////                        contentDescription = "Profile Icon",
////                        modifier = Modifier.size(48.dp),
////                        tint = MaterialTheme.colorScheme.primary
////                    )
////                    Spacer(modifier = Modifier.width(8.dp))
////                    Column {
////                        Text(
////                            text = "Welcome!",
////                            style = MaterialTheme.typography.bodyMedium,
////                            color = MaterialTheme.colorScheme.onBackground
////                        )
////                        Text(
////                            text = "User Name",
////                            style = MaterialTheme.typography.bodyLarge,
////                            color = MaterialTheme.colorScheme.primary
////                        )
////                    }
////                }
//
//                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f), thickness = 1.dp)
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Drawer Items
//                DrawerItem(icon = Icons.Default.Home, label = "Home") { /* Handle navigation */ }
//                DrawerItem(icon = Icons.Default.Person, label = "Profile") { /* Handle navigation */ }
//                DrawerItem(icon = Icons.Default.Settings, label = "Settings") { /* Handle navigation */ }
//                DrawerItem(icon = Icons.Default.Check, label = "Help") { /* Handle navigation */ }
//
////                Spacer(modifier = Modifier.weight(1f))
//
//                // Footer Section
//                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f), thickness = 1.dp)
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "App Version: 1.0.0",
//                    style = MaterialTheme.typography.labelSmall,
//                    color = MaterialTheme.colorScheme.onBackground,
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//            }
//        }
//
////        drawerContent = {
////                Column(modifier = Modifier.padding(16.dp).background(Color.White)) {
////                    Text(text = "Home")
////                    Text(text = "Profile")
////                    Text(text = "Settings")
////                }
////
////        }
//    ) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            // Google Map
//          loactions?.let {
//              GoogleMap(
//                  modifier = Modifier.fillMaxSize(),
//                  cameraPositionState = cameraPositionState,
//
//                  ){
//                  Marker(state = MarkerState(
////                      position = LatLng(loactions!!.latitude, loactions!!.longitude)
//                      position = loactions!!
//                  ))
//              }
//          }
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.TopCenter)
//                    .padding(bottom = 34.dp)
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 16.dp, horizontal = 16.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Box(
//                        contentAlignment = Alignment.Center,
//                        modifier = Modifier
//                            .size(50.dp) // Adjust size as needed
//                            .background(Color.White, shape = RoundedCornerShape(8.dp))
//                            .clickable {
//                                scope.launch {
//                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
//                                }
//                            }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Menu,
//                            contentDescription = "Menu Icon",
//                            tint = appcolr,
//                            modifier = Modifier
//                                .size(24.dp)
//                                .clickable {
//
////                                    navController.navigate("ConfirmScreen")
//                                }
//                        )
//                    }
//                    Icon(imageVector = Icons.Default.Notifications,
//                        tint = appcolr,
//                        contentDescription = "Notifications")
//
//
//                }
//            }
//
//            // Bottom Card
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.BottomCenter)
//                    .padding(bottom = 34.dp)
//            ) {
//                Card(
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color.White
//                    ),
//                    shape = RoundedCornerShape(16.dp),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp)
//                        .wrapContentHeight()
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp)
//                    ) {
//                        Row {
//                            Card(
//                                modifier = Modifier
//                                    .weight(1f) // Ensures equal size
//                                    .padding(8.dp)
//                                    .wrapContentHeight(),
//                                elevation = CardDefaults.cardElevation(8.dp),
//                                shape = RoundedCornerShape(8.dp),
//                                colors = CardDefaults.cardColors(containerColor = Color.White)
//                            ) {
//                                Row(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(8.dp),
//                                    verticalAlignment = Alignment.CenterVertically,
//                                    horizontalArrangement = Arrangement.Center
//                                ) {
//                                    Icon(
////                                        imageVector =  Icons.Default.Menu,
//                                        painter =  painterResource(id = com.o9tech.mytestafertoneday.R.drawable.local_car_wash),
//                                        contentDescription = "text",
//                                        tint = appcolr,
//                                        modifier = Modifier.size(24.dp)
//                                    )
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                Text(text = "Wash point", color = Color.Black)
////                                    Text(text = vehicledata.value?.totalItems.toString(), color = Color.Black)
//                                }
//                            }
//                            Card(
//                                modifier = Modifier
//                                    .weight(1f) // Ensures equal size
//                                    .padding(8.dp)
//                                    .wrapContentHeight(),
//                                elevation = CardDefaults.cardElevation(8.dp),
//                                shape = RoundedCornerShape(8.dp),
//                                colors = CardDefaults.cardColors(containerColor = Color.White)
//                            ) {
//                                Row(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(8.dp),
//                                    verticalAlignment = Alignment.CenterVertically,
//                                    horizontalArrangement = Arrangement.Center
//                                ) {
//                                    Icon(
////                                        painter = R.drawable.calendar_month,
//                                       painter =  painterResource(id = com.o9tech.mytestafertoneday.R.drawable.calendar_month),
////                                                painter = painterResource(id = R.drawable.calendar_month),
//                                        contentDescription = "text",
//                                        tint = appcolr,
//                                        modifier = Modifier.size(24.dp)
//                                    )
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    Text(text = "Schedule", color = Color.Black)
//                                }
//                            }
//                        }
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp),
//                            shape = RoundedCornerShape(8.dp),
//                            colors = CardDefaults.cardColors(containerColor = Color.White),
//                            elevation = CardDefaults.cardElevation(4.dp)
//                        ) {
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.Center
//                            ) {
//                                Icon(
////                                    imageVector = Icons.Default.Menu,
//                                    painter =  painterResource(id = com.o9tech.mytestafertoneday.R.drawable.baseline_location_on_24),
//                                    contentDescription = "Menu Icon",
//                                    tint = appcolr
//                                )
//                                TextField(
//                                    value = text,
//                                    onValueChange = { text = it },
//                                    placeholder = { Text(text = "Enter text here", fontSize = 14.sp, color = Color.Gray) },
//                                    singleLine = true,
//                                    colors = TextFieldDefaults.colors(
//                                        unfocusedContainerColor = Color.Transparent,
//                                        focusedIndicatorColor = Color.Transparent,
//                                        unfocusedIndicatorColor = Color.Transparent,
//                                        focusedContainerColor = Color.Transparent,
//                                    ),
//                                    textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = Color.Black)
//                                )
//                            }
//                        }
//
//                        Text(text = "Select Car Type", color = Color.Black, fontSize = 14.sp, modifier = Modifier.padding(horizontal = 16.dp))
//                        LazyRow(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp),
//                            horizontalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            vehicledata.value?.hydraMember?.let {
//                                items(it.size) { index -> // Adjust the number of items as needed
//                                    Card(
//                                        modifier = Modifier
//                                            .size(120.dp, 120.dp)
//                                            .clickable {
//                                                navController.navigate("ConfirmScreen")
//                                            },
//                                        shape = RoundedCornerShape(8.dp),
//                                        colors = CardDefaults.cardColors(containerColor = Color.White),
//                                        elevation = CardDefaults.cardElevation(4.dp)
//                                    ) {
//                                        Column(
//                                            modifier = Modifier
//                                                .fillMaxSize()
//                                                .padding(16.dp),
//                                            horizontalAlignment = Alignment.CenterHorizontally,
//                                            verticalArrangement = Arrangement.Center
//                                        ) {
//                                            Icon(
////                                                painter = painterResource(id = R.drawable.ic_menu_gallery),
//                                                painter =  painterResource(id = com.o9tech.mytestafertoneday.R.drawable.pathimage),
//
//                                                contentDescription = "Icon",
//                                                tint = appcolr,
//                                                modifier = Modifier.size(60.dp)
//                                            )
//                                            Spacer(modifier = Modifier.height(8.dp))
//                                            Text(
//                                                text = it[index].name,
//                                                fontSize = 14.sp,
//                                                color = Color.Black
//                                            )
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//
//}
//
//
//
//@Composable
//fun DrawerItem(icon: ImageVector, label: String, onClick: () -> Unit) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .clickable(onClick = onClick)
//    ) {
//        Icon(
//            imageVector = icon,
//            contentDescription = "$label Icon",
//            tint = MaterialTheme.colorScheme.primary,
//            modifier = Modifier.size(24.dp)
//        )
//        Spacer(modifier = Modifier.width(16.dp))
//        Text(
//            text = label,
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onBackground
//        )
//    }
//}
//
//
//
