package com.o9tech.mytestafertoneday.screens

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
import com.o9tech.mytestafertoneday.mainViewModel.FindWasherViewModel
import com.o9tech.mytestafertoneday.ui.theme.appcolr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindWasherScreen(navController: NavHostController, findWasherViewModel: FindWasherViewModel) {
    val cameraPositionState = rememberCameraPositionState()
    val userLocation by findWasherViewModel.userLocation.collectAsState()
    val context = LocalContext.current
//    val scaffoldState = rememberBottomSheetScaffoldState()
//    val sheetstat = rememberModalBottomSheetState
//    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
//            skipHiddenState = true
        )
    )
    val statuses = listOf(
        "Finding a washer",
        "Confirming your wash",
        "Connecting to washer"
    )
    var currentIndex by remember { mutableStateOf(0) }

    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            findWasherViewModel.fetchUserLocation(context, fusedLocationClient)
        } else {
            Log.e("MapScreen", "Location permission was denied by the user.")
        }
    }

    LaunchedEffect(Unit) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            -> {
                findWasherViewModel.fetchUserLocation(context, fusedLocationClient)
            }

            else -> {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    LaunchedEffect(true) {
        while (true) {
            kotlinx.coroutines.delay(3000)
            currentIndex = (currentIndex + 1) % statuses.size
        }
    }


    Scaffold(
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState
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

                }

                BottomSheetScaffold(
                    sheetContainerColor = Color.White,
                    scaffoldState = scaffoldState,
                    sheetContent = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
//                                text = "Finding washer",
                              text =   statuses[currentIndex],
                                color = appcolr,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(text = "Complete all fields to find the Washer close to your location. For example:", fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(12.dp))
                            Image(
                                painter = painterResource(id = R.drawable.groupi),
                                contentDescription = "result",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp).align(androidx.compose.ui.Alignment.CenterHorizontally).clickable {
                                        navController.navigate("WasherAssignScreen")
                                    }
                            )
                        }


                    }){}
            }
        }
    )
}