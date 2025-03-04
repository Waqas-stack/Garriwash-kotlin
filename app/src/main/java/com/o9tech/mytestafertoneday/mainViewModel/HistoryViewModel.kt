package com.o9tech.mytestafertoneday.mainViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o9tech.mytestafertoneday.data.model.HistoryModel.AllHistoyModel
import com.o9tech.mytestafertoneday.data.model.UpdateUser.UpdateUserModel
import com.o9tech.mytestafertoneday.mainRepositry.MainRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel@Inject constructor(private val mainRepositry: MainRepositry):ViewModel() {

    val history: StateFlow<AllHistoyModel?> = mainRepositry.history

    init {
        history()
    }

    fun history(){
        viewModelScope.launch {
            mainRepositry.allHistory()
        }
        }
    }

