package com.o9tech.mytestafertoneday.mainViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class SearchLocationViewModel @Inject constructor():ViewModel() {

    var searchQuery by mutableStateOf("")

    var placesList by mutableStateOf(listOf<String>())


    private val apiKey = "AIzaSyA2P5T0fbOOEY_zrK7AKOCrbeMQNYQ7j3Y"
    private val sessionToken = "12345"

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun fetchSuggestions(query: String) {
        viewModelScope.launch {
            if (query.isNotEmpty()) {
                val url =
                    "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=$query&key=$apiKey&sessiontoken=$sessionToken"

                val response = withContext(Dispatchers.IO) {
                    try {
                        URL(url).readText()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                }

                response?.let {
                    val jsonObject = JSONObject(it)
                    val predictions = jsonObject.getJSONArray("predictions")
                    val results = mutableListOf<String>()
                    for (i in 0 until predictions.length()) {
                        results.add(predictions.getJSONObject(i).getString("description"))
                    }
                    placesList = results
                }
            }
        }
    }

}




//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import org.json.JSONObject
//import java.net.URL
//
//class SearchLocationViewModel : ViewModel() {
//    var searchQuery by mutableStateOf("")
//        private set
//    var placesList by mutableStateOf(listOf<String>())
//        private set
//
//    private val apiKey = "YOUR_API_KEY_HERE"
//    private val sessionToken = "12345"
//
//    fun updateSearchQuery(query: String) {
//        searchQuery = query
//    }
//
//    fun fetchSuggestions(query: String) {
//        viewModelScope.launch {
//            if (query.isNotEmpty()) {
//                val url =
//                    "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=$query&key=$apiKey&sessiontoken=$sessionToken"
//
//                val response = withContext(Dispatchers.IO) {
//                    try {
//                        URL(url).readText()
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                        null
//                    }
//                }
//
//                response?.let {
//                    val jsonObject = JSONObject(it)
//                    val predictions = jsonObject.getJSONArray("predictions")
//                    val results = mutableListOf<String>()
//                    for (i in 0 until predictions.length()) {
//                        results.add(predictions.getJSONObject(i).getString("description"))
//                    }
//                    placesList = results
//                }
//            }
//        }
//    }
//}
