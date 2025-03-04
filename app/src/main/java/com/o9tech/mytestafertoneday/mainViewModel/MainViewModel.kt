package com.o9tech.mytestafertoneday.mainViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o9tech.mytestafertoneday.data.SharedPrefrenceManager.SharedPrefrenceMa
import com.o9tech.mytestafertoneday.data.model.loginmodels.LoginRequestModel
import com.o9tech.mytestafertoneday.data.model.loginmodels.LoginResponseModel
import com.o9tech.mytestafertoneday.data.model.signupmodels.SignUpResponseModel
import com.o9tech.mytestafertoneday.data.model.signupmodels.SignupRequestModel
import com.o9tech.mytestafertoneday.mainRepositry.MainRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepositry: MainRepositry,private val sharedPrefrenceMa: SharedPrefrenceMa) : ViewModel() {


    val login: StateFlow<LoginResponseModel?> = mainRepositry.login
    val signup: StateFlow<SignUpResponseModel?> = mainRepositry.signup

    fun getId(): String? {
        return sharedPrefrenceMa.getToken()
    }



    fun login(loginRequestModel: LoginRequestModel){
        viewModelScope.launch {
            mainRepositry.loginRequest(loginRequestModel)
        }
    }

    fun signup(signupRequestModel: SignupRequestModel){
        viewModelScope.launch {
            mainRepositry.signupRequest(signupRequestModel)
        }
    }

}