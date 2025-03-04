package com.o9tech.mytestafertoneday.mainRepositry

import android.util.Log
import androidx.navigation.NavHostController
import com.o9tech.mytestafertoneday.data.SharedPrefrenceManager.SharedPrefrenceMa
import com.o9tech.mytestafertoneday.data.apiservices.ApiServices
import com.o9tech.mytestafertoneday.data.di.GaariWashApi
import com.o9tech.mytestafertoneday.data.model.GetUserModel.GetUserDetails
import com.o9tech.mytestafertoneday.data.model.HistoryModel.AllHistoyModel
import com.o9tech.mytestafertoneday.data.model.UpdateUser.UpdateUserModel
import com.o9tech.mytestafertoneday.data.model.UpdateUserModelRequest.UpdateUserModelisRequest
import com.o9tech.mytestafertoneday.data.model.VehicleDetailsModel.VehicleDetailModel
import com.o9tech.mytestafertoneday.data.model.addPaymentModel.AddPaymentMethodsModel
import com.o9tech.mytestafertoneday.data.model.loginmodels.LoginRequestModel
import com.o9tech.mytestafertoneday.data.model.loginmodels.LoginResponseModel
import com.o9tech.mytestafertoneday.data.model.signupmodels.SignUpResponseModel
import com.o9tech.mytestafertoneday.data.model.signupmodels.SignupRequestModel
import com.o9tech.mytestafertoneday.data.model.washoption.WashpointModel
import com.o9tech.mytestafertoneday.data.model.washoptionsmodels.WashOptionModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainRepositry @Inject constructor(
    @GaariWashApi private val apiService: ApiServices,
    @GaariWashApi private val googlePlacesApiService: ApiServices,
    private val sharedPrefManager: SharedPrefrenceMa,
) {

    val _login = MutableStateFlow<LoginResponseModel?>(null)
    val login: StateFlow<LoginResponseModel?> = _login

    val _signup = MutableStateFlow<SignUpResponseModel?>(null)
    val signup: StateFlow<SignUpResponseModel?> = _signup

    val _vehicleData = MutableStateFlow<VehicleDetailModel?>(null)
    val vehicleData: StateFlow<VehicleDetailModel?> = _vehicleData

    val _washOptions = MutableStateFlow<WashOptionModel?>(null)
    val washOptions: StateFlow<WashOptionModel?> = _washOptions


    val _addPayment = MutableStateFlow<AddPaymentMethodsModel?>(null)
    val addPayment: StateFlow<AddPaymentMethodsModel?> = _addPayment

    val _washpoint = MutableStateFlow<WashpointModel?>(null)
    val washpoint: StateFlow<WashpointModel?> = _washpoint

    val _userdata = MutableStateFlow<GetUserDetails?>(null)
    val userdata: StateFlow<GetUserDetails?> = _userdata

    val _userdataUpdate = MutableStateFlow<UpdateUserModel?>(null)
    val userdataupdate: StateFlow<UpdateUserModel?> = _userdataUpdate

    val _history = MutableStateFlow<AllHistoyModel?>(null)
    val history: StateFlow<AllHistoyModel?> = _history

    val _googlePlaces = MutableStateFlow<List<String?>>(emptyList())
    val googlePlaces: StateFlow<List<String?>> = _googlePlaces


    suspend fun loginRequest(loginRequestModel: LoginRequestModel) {
        val response = apiService.loginApi(loginRequestModel)
        if (response.isSuccessful && response.body() != null) {
            _login.emit(response.body())
            Log.d("Loginres", "loginRequest: ${response.body()}")
            Log.d("Loginresid", "loginRequest: ${response.body()!!.user.id}")
            val cookies = response.headers()["Set-Cookie"]
            Log.d("cookies", "loginRequestcookies: $cookies")
            sharedPrefManager.saveToken(response.body()!!.user.id.toString())
            sharedPrefManager.saveCookies(cookies!!)
        } else {
            _login.emit(null)
        }
    }

    suspend fun signupRequest(signupRequestModel: SignupRequestModel) {
        val response = apiService.signupApi(signupRequestModel)
        if (response.isSuccessful && response.body() != null) {
            _signup.emit(response.body())
        } else {
            _signup.emit(null)
        }
    }

    suspend fun getVehicleData() {
        val response = apiService.getVehicleDetails()
        if (response.isSuccessful && response.body() != null) {
            _vehicleData.emit(response.body())
            Log.d("VehicleData", "_vehicleData: ${response.body()}")
        } else {
            _vehicleData.emit(null)
        }
    }

    suspend fun washOptions() {
        val response = apiService.washOptions()
        if (response.isSuccessful && response.body() != null) {
            _washOptions.emit(response.body())
            Log.d("washOptions", "washOptionsres: ${response.body()}")
        } else {
            _washOptions.emit(null)
        }
    }

    suspend fun addPayment() {
        val response = apiService.addPayment()
        if (response.isSuccessful && response.body() != null) {
            _addPayment.emit(response.body())
            Log.d("washOptions", "washOptionsres: ${response.body()}")
        } else {
            _addPayment.emit(null)
        }
    }

    suspend fun waspoint() {
        val response = apiService.washPoint()
        if (response.isSuccessful && response.body() != null) {
            _washpoint.emit(response.body())
            Log.d("washpoint", "washpointisthis: ${response.body()}")
        } else {
            _washpoint.emit(null)
        }
    }


    suspend fun getUserData() {
        var userId: String = sharedPrefManager.getToken().toString()
        var cookies: String = sharedPrefManager.getCookies().toString()
        val response = apiService.getUserDetail(userId, cookies)
        if (response.isSuccessful && response.body() != null) {
            _userdata.emit(response.body())
            Log.d("getUserData", "Userdata: ${response.body()}")
            Log.d("fullname", "Userdataname: ${response.body()!!.fullName}")
        } else {
            _userdata.emit(null)
        }
    }


    suspend fun updateUserdata(updateUserModelisRequest: UpdateUserModelisRequest) {
        var userId: String = sharedPrefManager.getToken().toString()
        var cookies: String = sharedPrefManager.getCookies().toString()
        val response = apiService.updateUserDetails(userId, updateUserModelisRequest, cookies)
        if (response.isSuccessful && response.body() != null) {
            _userdataUpdate.emit(response.body())
            Log.d("updateUser", "update User data: ${response.body()}")
//            Log.d("fullname", "Userdataname: ${response.body()!!.fullName}")
        } else {
            _userdataUpdate.emit(null)
        }

    }


    suspend fun allHistory() {
        var cookies: String = sharedPrefManager.getCookies().toString()
        val response = apiService.history(cookies)
        if (response.isSuccessful && response.body() != null) {
            _history.emit(response.body())
        } else {
            _history.emit(null)
        }


    }

    suspend fun googlePlacesApi(input: String, key: String, sessiontoken: String) {

        Log.d("GooglePlacesAPI", "Input: $input")
        Log.d("GooglePlacesAPI", "Key: $key")
        Log.d("GooglePlacesAPI", "Session Token: $sessiontoken")

        val response = googlePlacesApiService.getLocation(input, key, sessiontoken)
        if (response.isSuccessful && response.body() != null) {

            val suggestions = response.body()!!
                .getAsJsonArray("predictions")
                .map { it.asJsonObject.get("description").asString }

//            _googlePlaces.emit(response.body()!!.predictions.map { it.description })
//            _googlePlaces.emit(
//                response.body()!!.getAsJsonArray("predictions")
//                    .map { it.asJsonObject.get("description").asString })
            Log.d("GooglePlacesAPI", "Suggestions: $suggestions")
            println("Suggestionsiss: $suggestions")


            _googlePlaces.emit(suggestions)

        } else {
            _googlePlaces.emit(emptyList())
        }
    }


    fun logout(navController: NavHostController) {
        sharedPrefManager.clearToken()
        sharedPrefManager.clearCookies()
        navController.navigate("LoginScreen") {
            popUpTo(0) { inclusive = true } // Remove all previous destinations
        }

    }

    suspend fun confirmOrder() {
//    val response = apiService.confirmOrder()

    }

}