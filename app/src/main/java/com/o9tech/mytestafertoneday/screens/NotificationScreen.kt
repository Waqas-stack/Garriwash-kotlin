package com.o9tech.mytestafertoneday.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.o9tech.mytestafertoneday.ui.theme.appcolr


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NotificationScreens(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text("Notification")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack,
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
                    .padding(it),
            ) {
               LazyColumn (
                   verticalArrangement = Arrangement.spacedBy(10.dp),
                   modifier = Modifier.padding(10.dp),
                   horizontalAlignment = Alignment.CenterHorizontally,
               ){
                   items(10){
                       Card(
                           modifier = Modifier
                               .fillMaxWidth() // Card takes full width
                               .padding(12.dp), // Padding around the card
                           shape = RoundedCornerShape(16.dp), // Rounded corners for the card
                           elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp) // Elevation for shadow
                       ) {
                           // Column to arrange Text items vertically
                           Column(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(horizontal = 16.dp) // Horizontal padding
                                   .height(100.dp), // Adjust the height of the card
                               verticalArrangement = Arrangement.Center, // Vertically center the content
                               horizontalAlignment = Alignment.Start // Align text to the start horizontally
                           ) {
                               Text("System", style = MaterialTheme.typography.bodyLarge)
                               Spacer(modifier = Modifier.height(8.dp)) // Space between the text
                               Text("your booking has been cancelled", style = MaterialTheme.typography.bodyMedium)
                           }
                       }
                   }
               }
            }
        }
    )
}



//@Composable
//fun CardWithText() {
//    // Card container
//    Card(
//        modifier = Modifier
//            .fillMaxWidth() // Card takes full width
//            .padding(16.dp), // Padding around the card
//        shape = RoundedCornerShape(16.dp), // Rounded corners for the card
//        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp) // Elevation for shadow
//    ) {
//        // Column to arrange Text items vertically
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp) // Horizontal padding
//                .height(100.dp), // Adjust the height of the card
//            verticalArrangement = Arrangement.Center, // Vertically center the content
//            horizontalAlignment = Alignment.Start // Align text to the start horizontally
//        ) {
//            Text("Text 1", style = MaterialTheme.typography.bodyMedium)
//            Spacer(modifier = Modifier.height(8.dp)) // Space between the text
//            Text("Text 2", style = MaterialTheme.typography.bodyMedium)
//        }
//    }
//}
