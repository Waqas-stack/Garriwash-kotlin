package com.o9tech.mytestafertoneday.screens.maps

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.o9tech.mytestafertoneday.data.model.GetUserModel.GetUserDetails
import com.o9tech.mytestafertoneday.data.model.UpdateUser.UpdateUserModel
import com.o9tech.mytestafertoneday.data.model.UpdateUserModelRequest.UpdateUserModelisRequest
import com.o9tech.mytestafertoneday.data.model.VehicleDetailsModel.VehicleDetailModel
import com.o9tech.mytestafertoneday.mainRepositry.MainRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(private val mainRepository: MainRepositry) : ViewModel() {

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation: StateFlow<LatLng?> = _userLocation


    private val _userAddress = MutableStateFlow<String?>(null)
    val userAddress: StateFlow<String?> = _userAddress

    val vehicleData: StateFlow<VehicleDetailModel?> = mainRepository.vehicleData
    val userData: StateFlow<GetUserDetails?> = mainRepository.userdata
    val updateUserData: StateFlow<UpdateUserModel?> = mainRepository.userdataupdate
    val places: StateFlow<List<String?>> = mainRepository.googlePlaces

    init {
        getVehicle()
        getUserdata()
    }

    fun fetchUserLocation(context: Context, fusedLocationClient: FusedLocationProviderClient) {
        // Check if the location permission is granted
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                // Fetch the last known location
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        // Update the user's location in the state
                        val userLatLng = LatLng(it.latitude, it.longitude)
                        _userLocation.value = userLatLng
                        updateAddress(context, userLatLng)
                        Log.d("Locationss", "fetchUserLocation: ${_userLocation.value}")
                    }
                }
            } catch (e: SecurityException) {
//                Timber.e("Permission for location access was revoked: ${e.localizedMessage}")
                Log.e(
                    "MapViewModel",
                    "Permission for location access was revoked: ${e.localizedMessage}"
                )
            }
        } else {
//            Timber.e("Location permission is not granted.")
            Log.e("MapViewModel", "Location permission is not granted.")
        }
    }

    private val placeApiKey = "AIzaSyA2P5T0fbOOEY_zrK7AKOCrbeMQNYQ7j3Y"
    private var sessionToken = MutableStateFlow<String?>(null)

    fun onTextChanged(input: String) {
        println("onTextChanged: $input")
        Log.d("valessss", "onTextChanged:$input ")
        // Generate a new session token if it's null
        if (sessionToken.value == null) {
            sessionToken.value = UUID.randomUUID().toString()
        }
        getplace(input)
    }

    fun getplace(input: String){
        viewModelScope.launch {
            mainRepository.googlePlacesApi(input,placeApiKey, sessionToken.value ?: "")
        }
    }


    private fun updateAddress(context: Context, loc: LatLng) {
        viewModelScope.launch {
            _userAddress.value = getAddressFromLatLng(context, loc)
        }
    }

    fun getAddressFromLatLng(context: Context, loc: LatLng): String {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(loc.latitude, loc.longitude, 1)
            if (addresses.isNullOrEmpty()) {
                "No address found"
            } else {
                addresses[0].getAddressLine(0)

//                val address = addresses[0]
//                "${address.getAddressLine(0)}"
            }
        } catch (e: Exception) {
            Log.e("Geocoder Error", e.localizedMessage ?: "Unknown error")
            "Unable to fetch address"
        }
    }


    fun getVehicle() {
        viewModelScope.launch {
            mainRepository.getVehicleData()
        }
    }

    fun getUserdata() {
        viewModelScope.launch {
            mainRepository.getUserData()
        }

    }

    fun updateUserData(updateUserModelisRequest: UpdateUserModelisRequest) {
        viewModelScope.launch {
            mainRepository.updateUserdata(updateUserModelisRequest)
        }
    }

    fun logout(navController: NavHostController) {
        viewModelScope.launch {
            mainRepository.logout(navController)
        }
    }
}


