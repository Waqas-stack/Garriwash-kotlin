package com.o9tech.mytestafertoneday.mainViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o9tech.mytestafertoneday.data.model.signupmodels.SignUpResponseModel
import com.o9tech.mytestafertoneday.data.model.washoption.WashpointModel
import com.o9tech.mytestafertoneday.mainRepositry.MainRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WashpointViewModel @Inject constructor(private val mainRepositry: MainRepositry) :ViewModel() {


    val waspoint: StateFlow<WashpointModel?> = mainRepositry.washpoint


    init {
        getwashpoint()
    }

    fun getwashpoint(){
        viewModelScope.launch {
            mainRepositry.waspoint()
        }
    }
}