package com.o9tech.mytestafertoneday.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.o9tech.mytestafertoneday.R
import com.o9tech.mytestafertoneday.screens.maps.MapViewModel
import com.o9tech.mytestafertoneday.ui.theme.appcolr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLocation(navController: NavHostController, mapViewModel: MapViewModel) {
    var searchtext by remember { mutableStateOf("") }

    val userLocation by mapViewModel.places.collectAsState()

    Scaffold (
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text("Search Location")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            tint = appcolr,
                            contentDescription = "Menu Icon")
                    }
                },

                )
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(0.dp)
                ) {
                    TextField(
                        value = searchtext,
                        onValueChange = {vasl->
                            searchtext = vasl
                            mapViewModel.onTextChanged(vasl)
                                        },
                        placeholder = { Text(text = "Search Location", fontSize = 14.sp, color = Color.Gray) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(onClick = {
                                mapViewModel.onTextChanged(searchtext)
                            }) {
                                Icon(
                                    painter =  painterResource(id = R.drawable.search),
                                    contentDescription = "Menu Icon",
                                    tint = appcolr
                                )
                            }

                        },
                        leadingIcon = {
                                      Icon(
                                           painter =  painterResource(id = R.drawable.baseline_location_on_24),
                                           contentDescription = "Menu Icon",
                                           tint = appcolr
                                       )
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            disabledTextColor = Color.Black,
                            disabledPlaceholderColor = Color.Black
                        ),
                        textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = Color.Black)
                    )
                    Divider()
                    
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp)

                    ){
                        Icon(
                            painter =  painterResource(id = R.drawable.baseline_location_on_24),
                            contentDescription = "Menu Icon",
                            tint = appcolr
                        )
                        Spacer(modifier = Modifier.width(18.dp))

                        Text(text = "Search on map",fontSize = 16.sp, color = Color.Black)
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()

                    ) {
                        items(userLocation.size){
                            ListItem(
                                colors = ListItemDefaults.colors(
                                    containerColor = Color.White

                                ),
                                headlineContent = {
                                Text(text = "Location $it", fontSize = 16.sp, color = Color.Black)
                            })
                        }
                    }
                }
            }
        }
    )
}