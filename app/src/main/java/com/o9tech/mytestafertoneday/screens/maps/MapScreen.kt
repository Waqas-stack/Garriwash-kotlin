package com.o9tech.mytestafertoneday.screens.maps

import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.o9tech.mytestafertoneday.R
import com.o9tech.mytestafertoneday.ui.theme.appcolr
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings

@Composable
fun MapScreen(mapViewModel: MapViewModel, navController: NavHostController) {

    val selectedIndex = remember { mutableStateOf(-1) }
    var showDialog by remember { mutableStateOf(false) }


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    var selectedItemIndex by rememberSaveable {
//        mutableStateOf(0)
//    }

    val scope = rememberCoroutineScope()


    val cameraPositionState = rememberCameraPositionState()
    val context = LocalContext.current
    val userLocation by mapViewModel.userLocation.collectAsState()
    val userAdress by mapViewModel.userAddress.collectAsState()
    val vehicledata = mapViewModel.vehicleData.collectAsState()
    val userdata = mapViewModel.userData.collectAsState()
    var text by remember { mutableStateOf(userAdress ?:"") }
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }


    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            mapViewModel.fetchUserLocation(context, fusedLocationClient)
        } else {
            Log.e("MapScreen", "Location permission was denied by the user.")
        }
    }


    LaunchedEffect(userAdress) {
        text = userAdress ?: "Fetching address..."
    }

    LaunchedEffect(Unit) {
        text = userAdress ?: "Fetching address..."
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            -> {
                mapViewModel.fetchUserLocation(context, fusedLocationClient)
            }

            else -> {
                permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }











    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White,
                drawerContentColor = Color.Black,
                drawerShape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                drawerTonalElevation = 0.dp,
                modifier = Modifier.width(300.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(color = appcolr).clickable {
                            navController.navigate("EditProfile")
//                            navController.navigate("EditProfile/${userdata.value}")


                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Profile Picture
                        Image(
                            painter = painterResource(id = R.drawable.profile), // Replace with your profile image
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(8.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // Name
                        Text(
                            text = userdata.value?.fullName ?:"Imran Ashraf",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        // Phone Number
                        Text(
                            text = userdata.value?.phone ?:"+1234567890",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = {
                        scope.launch {
//                            navController.navigate("home")
                        }
                    },
                    icon = { Icon(Icons.Outlined.Home, contentDescription = "Home",tint = appcolr) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text("History") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            navController.navigate("HistoryScreen")

                        }
                    },
                    icon = { Icon(Icons.Outlined.DateRange, contentDescription = "History",tint = appcolr) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text("About Us") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            navController.navigate("AboutUsScreen")
                        }
                    },
                    icon = { Icon(Icons.Outlined.AccountBox, contentDescription = "About us",tint = appcolr) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text("Notifications") },
                    selected = false,
                    onClick = {
                        scope.launch {

                            navController.navigate("NotificationScreens")
                        }
                    },
                    icon = { Icon(Icons.Outlined.Notifications, contentDescription = "Notifications",tint = appcolr) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                NavigationDrawerItem(
                    label = { Text("Rate App") },
                    selected = false,
                    onClick = {
                        scope.launch {
//                            navController.navigate("home")
                            navController.popBackStack()
                        }
                    },
                    icon = { Icon(Icons.Outlined.Star, contentDescription = "Rate App",tint = appcolr) },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

                Spacer(modifier = Modifier.weight(1f))

                NavigationDrawerItem(
                    label = { Text("Sign Out") },
                    selected = false,
                    onClick = {
                        showDialog = true // Trigger the dialog
                        scope.launch { drawerState.close() } // Close the drawer

                    },
                    icon = {
                        Icon(Icons.Outlined.ExitToApp, contentDescription = "Sign Out",tint = appcolr)
                    }
                )
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {



                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        properties = MapProperties(
                            isMyLocationEnabled = true,
//                            isTrafficEnabled = true
                            isBuildingEnabled = true,
                        ),
                        uiSettings = MapUiSettings(
                            zoomControlsEnabled = false,
                            myLocationButtonEnabled = false,
                            mapToolbarEnabled = false,
                            scrollGesturesEnabled = true,
                            compassEnabled = true,
                            zoomGesturesEnabled = true,


                        )
                    ) {
                        userLocation?.let {
                            Marker(
                                state = MarkerState(position = it),
                                title = "Your Location",
                                snippet = "This is where you are currently located."
                            )
                            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 14f)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 34.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp, horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(40.dp) // Adjust size as needed
                                    .shadow(8.dp, shape = RoundedCornerShape(8.dp)) // Add elevation with shadow
                                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                                    .clickable {
                                        scope.launch {
                                            drawerState.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }
                                    }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu Icon",
                                    tint = appcolr,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Icon(imageVector = Icons.Outlined.Notifications,
                                tint = appcolr,
                                contentDescription = "Notifications",
                                modifier = Modifier.size(30.dp).clickable {
                                    navController.navigate("NotificationScreens")

                                }
                            )


                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 34.dp)
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .wrapContentHeight()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Row {
                                    Card(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(8.dp)
                                            .wrapContentHeight().clickable {
                                                navController.navigate("WashPointScreen")
                                            },
                                        elevation = CardDefaults.cardElevation(8.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Icon(
                                                painter =  painterResource(id = R.drawable.local_car_wash),
                                                contentDescription = "text",
                                                tint = appcolr,
                                                modifier = Modifier.size(24.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(text = "Wash point", color = Color.Black)
                                        }
                                    }
                                    Card(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(8.dp)
                                            .wrapContentHeight().clickable {
                                                navController.navigate("ReviewScreenIs")

                                            },
                                        elevation = CardDefaults.cardElevation(8.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Icon(
                                                painter =  painterResource(id = R.drawable.calendar_month),
                                                contentDescription = "text",
                                                tint = appcolr,
                                                modifier = Modifier.size(24.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(text = "Schedule", color = Color.Black)
                                        }
                                    }
                                }
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp).clickable {
                                            navController.navigate("SearchLocation")
                                        },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    elevation = CardDefaults.cardElevation(4.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            painter =  painterResource(id = R.drawable.baseline_location_on_24),
                                            contentDescription = "Menu Icon",
                                            tint = appcolr
                                        )
                                        TextField(
                                            value = text,
                                            onValueChange = { text = it },
                                            placeholder = { Text(text = "Enter text here", fontSize = 14.sp, color = Color.Gray) },
                                            singleLine = true,
                                            modifier = Modifier
                                                .horizontalScroll(rememberScrollState())  // Enable horizontal scrolling
                                                .width(300.dp),
                                            enabled = false,
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

                                Text(text = "Select Car Type", color = Color.Black, fontSize = 14.sp, modifier = Modifier.padding(horizontal = 16.dp))

                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    vehicledata.value?.hydraMember?.let {
                                        items(it.size) { index ->
                                            val isSelected = selectedIndex.value == index
                                            Card(
                                                modifier = Modifier
                                                    .size(100.dp, 100.dp)
                                                    .clickable {
                                                        selectedIndex.value = index
                                                        navController.navigate("ConfirmScreen/$userAdress/$index")
                                                    },
                                                shape = RoundedCornerShape(8.dp),
                                                colors = CardDefaults.cardColors(
                                                    containerColor = if (isSelected) appcolr else Color.White
                                                ),
                                                elevation = CardDefaults.cardElevation(4.dp)
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(16.dp),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = Arrangement.Center
                                                ) {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.pathimage),
                                                        contentDescription = "Icon",
                                                        tint = if (isSelected) Color.White else appcolr,
                                                        modifier = Modifier.size(50.dp)
                                                    )
                                                    Spacer(modifier = Modifier.height(0.dp))
                                                    Text(
                                                        text = it[index].name,
                                                        fontSize = 14.sp,
                                                        color = if (isSelected) Color.White else Color.Black
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth().align(Alignment.CenterEnd)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding( horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Spacer(modifier = Modifier.width(1.dp))
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(40.dp) // Adjust size as needed
                                    .shadow(8.dp, shape = RoundedCornerShape(8.dp)) // Add elevation with shadow
                                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                                    .clickable {
                                        scope.launch {
//                                            drawerState.apply {
//                                                if (isClosed) open() else close()
//                                            }
                                        }
                                    }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.my_location),
//                                    imageVector = Icons.Default.,
                                    contentDescription = "my_location",
                                    tint = appcolr,
                                    modifier = Modifier.size(20.dp)
                                )
                            }



                        }
                    }

                    if (showDialog) {
                        AlertDialog(
                            containerColor = Color.White,
                            onDismissRequest = {},
                            title = {
                                Text(text = "Sign Out")
                            },
                            text = {
                                Text(text = "Are you sure you want to sign out?")
                            },
                            confirmButton = {
                                TextButton(
                                    colors = ButtonDefaults.textButtonColors(
                                        contentColor = appcolr
                                    ),
                                    onClick = {
                                        mapViewModel.logout(navController)
                                    }) {
                                    Text("Yes")
                                }
                            },
                            dismissButton = {

                                TextButton(
                                    colors = ButtonDefaults.textButtonColors(
                                        contentColor = Color.Red
                                    ),
                                    onClick = {
                                    showDialog = false
                                }) {
                                    Text("Cancel")
                                }
                            }
                        )
                    }
                }
            }
        )
    }
}


