package com.o9tech.mytestafertoneday.data.apiservices

import com.google.gson.JsonObject
import com.o9tech.mytestafertoneday.data.model.GetUserModel.GetUserDetails
import com.o9tech.mytestafertoneday.data.model.HistoryModel.AllHistoyModel
import com.o9tech.mytestafertoneday.data.model.UpdateUser.UpdateUserModel
import com.o9tech.mytestafertoneday.data.model.UpdateUserModelRequest.UpdateUserModelisRequest
import com.o9tech.mytestafertoneday.data.model.VehicleDetailsModel.VehicleDetailModel
import com.o9tech.mytestafertoneday.data.model.addPaymentModel.AddPaymentMethodsModel
import com.o9tech.mytestafertoneday.data.model.confirmOrderRequest.ConfirmRequest
import com.o9tech.mytestafertoneday.data.model.loginmodels.LoginRequestModel
import com.o9tech.mytestafertoneday.data.model.loginmodels.LoginResponseModel
import com.o9tech.mytestafertoneday.data.model.signupmodels.SignUpResponseModel
import com.o9tech.mytestafertoneday.data.model.signupmodels.SignupRequestModel
import com.o9tech.mytestafertoneday.data.model.washoption.WashpointModel
import com.o9tech.mytestafertoneday.data.model.washoptionsmodels.WashOptionModel
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {


    @POST("login")
    @Headers("Content-Type: application/json")
    suspend fun loginApi(
        @Body loginRequestModel: LoginRequestModel
    ):Response<LoginResponseModel>


    @POST("users")
    @Headers("Content-Type: application/json", "Accept: application/json")
    suspend fun signupApi(
        @Body signupRequestModel: SignupRequestModel
    ):Response<SignUpResponseModel>

    @GET("vehicle_types?page=1")
//    @Headers("Content-Type: application/json", "Accept: application/json")
    suspend fun getVehicleDetails():Response<VehicleDetailModel>

    @GET("wash_options?page=1")
    suspend fun washOptions():Response<WashOptionModel>


    @GET("payment_methods?page=1")
    suspend fun addPayment():Response<AddPaymentMethodsModel>

    @GET("wash_points?page=1")
    suspend fun washPoint():Response<WashpointModel>


    @POST("orders")
    suspend fun confirmOrder(
        @Body confirmRequest: ConfirmRequest
    ):Response<ResponseBody>


    @GET("users/{userid}")
    suspend fun getUserDetail(
        @Path("userid") userid:String,
        @Header("Cookie") cookies:String
    ):Response<GetUserDetails>


    @PATCH("users/{userid}")
    suspend fun updateUserDetails(
        @Path("userid") userid:String,
        @Body updateUserModelisRequest: UpdateUserModelisRequest,
        @Header("Cookie") cookies:String
    ):Response<UpdateUserModel>


    @GET("orders?page=1")
    suspend fun history(
        @Header("Cookie") cookies:String
    ):Response<AllHistoyModel>


    @GET("autocomplete/json")
    suspend fun getLocation(
        @Query("input") input:String,
        @Query("key") key:String,
        @Query("sessiontoken") sessiontoken:String
    ):Response<JsonObject>

}