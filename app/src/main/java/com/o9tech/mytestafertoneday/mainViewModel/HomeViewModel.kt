//package com.o9tech.mytestafertoneday.mainViewModel
//
//import android.Manifest
//import android.app.Activity
//import android.app.Application
//import android.content.pm.PackageManager
//import android.util.Log
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.maps.model.LatLng
//import com.o9tech.mytestafertoneday.data.model.VehicleDetailsModel.VehicleDetailModel
//import com.o9tech.mytestafertoneday.data.model.loginmodels.LoginResponseModel
//import com.o9tech.mytestafertoneday.mainRepositry.MainRepositry
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//
//@HiltViewModel
//class HomeViewModel @Inject constructor(private val mainRepository: MainRepositry,private val application: Application) : ViewModel() {
//
//
//    val vehicleData: StateFlow<VehicleDetailModel?> = mainRepository.vehicleData
//
////
//    private val _location = MutableStateFlow<LatLng?>(null)
//    val location: StateFlow<LatLng?> = _location
//
//    private val fusedLocationProviderClient =
//        LocationServices.getFusedLocationProviderClient(application)
//
////    private val _permissionGranted = MutableLiveData<Boolean>()
////    val permissionGranted: LiveData<Boolean> = _permissionGranted
//
//
////    private val _location = MutableLiveData<LatLng?>()
////    val location: LiveData<LatLng?> = _location
//
//
//    init {
//        getVehicle()
//    }
//
//    fun getVehicle() {
//        viewModelScope.launch {
//            mainRepository.getVehicleData()
//        }
//    }
//
//
//
//
//
//
//
//
//
//    fun getLocation() {
//        val permissionGranted = ContextCompat.checkSelfPermission(
//            application,
//            android.Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//
//        if (permissionGranted) {
//            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
//                location?.let {
//                    viewModelScope.launch {
//                        _location.emit(LatLng(it.latitude, it.longitude))
//                        Log.d("Loinhg", "getLocation: ${it.latitude} ${it.longitude}")
//                    }
//
//                }
//            }
//        }
//    }
//
//    fun requestLocationPermission(activity: Activity) {
//        ActivityCompat.requestPermissions(
//            activity,
//            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
//            1000
//        )
//    }
//
//
////    private fun checkLocationPermission() {
////        // Check if the permission is granted or not
////        if (ContextCompat.checkSelfPermission(application.applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION)
////            == PackageManager.PERMISSION_GRANTED) {
////            _permissionGranted.value = true
////        } else {
////            _permissionGranted.value = false
////        }
////    }
////
////    fun requestLocation() {
////        if (ContextCompat.checkSelfPermission(application, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
////        ) {
////            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
////                location?.let {
////                    _currentLocation.value = LatLng(it.latitude, it.longitude)
////                }
////            }
////        }
////    }
////
////
////    fun requestPermission(activity: Activity) {
////        ActivityCompat.requestPermissions(
////            activity,
////            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
////            1000
////        )
////    }
//}