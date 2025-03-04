package com.o9tech.mytestafertoneday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.o9tech.mytestafertoneday.mainViewModel.AddPaymentViewModel
import com.o9tech.mytestafertoneday.mainViewModel.ConfirmOrderViewModel
import com.o9tech.mytestafertoneday.mainViewModel.FindWasherViewModel
import com.o9tech.mytestafertoneday.mainViewModel.HistoryViewModel
import com.o9tech.mytestafertoneday.mainViewModel.MainViewModel
import com.o9tech.mytestafertoneday.mainViewModel.WasherAssignViewModel
import com.o9tech.mytestafertoneday.mainViewModel.WashpointViewModel
import com.o9tech.mytestafertoneday.screens.AboutUsScreen
import com.o9tech.mytestafertoneday.screens.AddPaymentMethod
import com.o9tech.mytestafertoneday.screens.ConfirmScreen
import com.o9tech.mytestafertoneday.screens.EditProfile
import com.o9tech.mytestafertoneday.screens.FindWasherScreen
import com.o9tech.mytestafertoneday.screens.ForgotPasswordScreen
import com.o9tech.mytestafertoneday.screens.HistoryScreen

import com.o9tech.mytestafertoneday.screens.LoginScreen
import com.o9tech.mytestafertoneday.screens.NotificationScreens
import com.o9tech.mytestafertoneday.screens.ReviewScreenIs
import com.o9tech.mytestafertoneday.screens.ScheduleScreen
import com.o9tech.mytestafertoneday.screens.SearchLocation
import com.o9tech.mytestafertoneday.screens.SignupScreen
import com.o9tech.mytestafertoneday.screens.WashPointScreen
import com.o9tech.mytestafertoneday.screens.WasherAssignScreen
import com.o9tech.mytestafertoneday.screens.maps.MapScreen
import com.o9tech.mytestafertoneday.screens.maps.MapViewModel
import com.o9tech.mytestafertoneday.ui.theme.MyTestAfertoneDayTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        installSplashScreen()
        setContent {

            val navController = rememberNavController()
            val mainViewModel: MainViewModel = viewModel()
            val findWasherViewModel: FindWasherViewModel = viewModel()
            val washerAssignViewModel: WasherAssignViewModel = viewModel()
            val mapViewModel: MapViewModel = viewModel()
            val historyViewModel: HistoryViewModel = viewModel()
            val confirmOrderViewModel: ConfirmOrderViewModel = viewModel()
            val addPaymentViewModel:AddPaymentViewModel= viewModel()
            val washpointViewModel: WashpointViewModel = viewModel()
            val startDestination = remember {
                if (mainViewModel.getId().isNullOrEmpty()) "LoginScreen" else "MyHomeScreen"
            }
            MyTestAfertoneDayTheme {
                NavHost(navController =navController , startDestination = startDestination) {
                    composable("LoginScreen"){
                        LoginScreen(navController,mainViewModel)
                    }
                    composable("SignupScreen"){
                        SignupScreen(navController,mainViewModel)
                    }
                    composable("ForgotPasswordScreen"){
                        ForgotPasswordScreen(navController)
                    }
                    composable("MyHomeScreen"){
//                        MapScreen(navController,mainViewModel,)
                        MapScreen(mapViewModel,navController)

                    }


                    composable("ConfirmScreen/{userAdress}/{selectedIndex}"){
                        val userAdress = it.arguments?.getString("userAdress") ?: ""
//                        val selectedIndex = it.arguments?.getInt("selectedIndex") ?: -1
                        val selectedIndex = it.arguments?.getString("selectedIndex")?.toIntOrNull() ?: -1

                        ConfirmScreen(navController,confirmOrderViewModel,userAdress,selectedIndex)
                    }
                    composable("AddPaymentMethod"){
                        AddPaymentMethod(navController,addPaymentViewModel)
                    }
                    composable("FindWasherScreen"){
                        FindWasherScreen(navController,findWasherViewModel)
                    }
                    composable("SearchLocation"){
                        SearchLocation(navController,mapViewModel)
                    }

                    composable("WashPointScreen"){
                        WashPointScreen(navController,washpointViewModel)
                    }

                    composable("ScheduleScreen"){
                        ScheduleScreen(navController)
                    }
                    composable("WasherAssignScreen"){
                        WasherAssignScreen(navController,washerAssignViewModel)
                    }
                    composable("ReviewScreenIs"){
                        ReviewScreenIs()
                    }
                    composable("EditProfile"){
                        EditProfile(navController,mapViewModel)
                    }
                    composable("NotificationScreens"){
                        NotificationScreens(navController)
                    }
                    composable("HistoryScreen"){
                        HistoryScreen(navController,historyViewModel)
                    }
                    composable("AboutUsScreen"){
                        AboutUsScreen(navController)
                    }

                }
                //AboutUsScreen
                //HistoryScreen
//                NotificationScreens
//                ConfirmScreen(navController,confirmOrderViewModel)
//                MyHomeScreen(navController,homeViewModel,LocalContext.current as Activity)
//                MapScreen(mapViewModel,navController)
//                NavigationDrawer()
//                HistoryScreen()
//                EditProfile()
//                WashTracking()
//                AddPaymentMethod()

            }
        }
    }
}

