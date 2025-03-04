package com.o9tech.mytestafertoneday.mainViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o9tech.mytestafertoneday.data.model.addPaymentModel.AddPaymentMethodsModel
import com.o9tech.mytestafertoneday.mainRepositry.MainRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddPaymentViewModel @Inject constructor(private val mainRepositry: MainRepositry) :ViewModel() {

    val payment : StateFlow<AddPaymentMethodsModel?> = mainRepositry.addPayment

    init {
        addPay()
    }


    fun addPay(){
        viewModelScope.launch {
            mainRepositry.addPayment()
        }
    }
}