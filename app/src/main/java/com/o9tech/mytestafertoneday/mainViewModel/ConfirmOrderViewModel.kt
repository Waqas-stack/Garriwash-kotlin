package com.o9tech.mytestafertoneday.mainViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o9tech.mytestafertoneday.data.model.VehicleDetailsModel.VehicleDetailModel
import com.o9tech.mytestafertoneday.data.model.washoptionsmodels.WashOptionModel
import com.o9tech.mytestafertoneday.mainRepositry.MainRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmOrderViewModel @Inject constructor(private val mainRepositry: MainRepositry) :ViewModel() {

    val vehicleData : StateFlow<VehicleDetailModel?> = mainRepositry.vehicleData
    val washOptions : StateFlow<WashOptionModel?> = mainRepositry.washOptions

    init {
        getVehicle()
        getWash()
    }



    fun getVehicle(){
        viewModelScope.launch {
            mainRepositry.getVehicleData()
        }
    }

    fun getWash(){
        viewModelScope.launch {
            mainRepositry.washOptions()
        }

    }

    fun confirmOrder(){
        viewModelScope.launch {
            mainRepositry.confirmOrder()

        }
    }


}