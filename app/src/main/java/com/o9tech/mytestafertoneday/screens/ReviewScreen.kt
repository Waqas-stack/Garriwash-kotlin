package com.o9tech.mytestafertoneday.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.o9tech.mytestafertoneday.R
import com.o9tech.mytestafertoneday.data.model.loginmodels.LoginRequestModel
import com.o9tech.mytestafertoneday.ui.theme.PurpleGrey40
import com.o9tech.mytestafertoneday.ui.theme.appcolr


@Preview(showBackground = true)
@Composable
fun ReviewScreenIs(){
    var rating: Float by remember { mutableStateOf(3.2f) }
    Scaffold (
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(color = Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(130.dp)
                    ) {
                        // Profile Picture
                        Image(
                            painter = painterResource(id = R.drawable.washtracking), // Replace with your image
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .offset(y = (59).dp)
                                .background(
                                    color = PurpleGrey40,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 4.dp, vertical = 4.dp)
                        ) {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ){

                                Text(
                                    text = "4.5",
                                    color = Color.White,
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Star",
                                    color = Color.White,
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(imageVector = Icons.Default.Star,
                                    contentDescription = "star",
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.White
                                )

                            }
                        }

                    }



                    Spacer(modifier = Modifier.height(26.dp))
                    Text(text = "Test Washer", fontSize = 24.sp, fontWeight = FontWeight.W400)
                    Text(text = "Passionate about my work.Lead my lifestyle in my own way", fontSize = 16.sp, textAlign = TextAlign.Center )
                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(text = "Poor", fontSize = 24.sp, fontWeight = FontWeight.W400)
                    Text(
                        text = getRatingText(rating.toInt()),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W400,

                    )

                    RatingBar(

                        value = rating,
                        painterEmpty = painterResource(id = R.drawable.star_border),
                        painterFilled = painterResource(id = R.drawable.star),
                        size = 34.dp,
                        onValueChange = {
                            rating = it
                        },
                        onRatingChanged = {
                            Log.d("TAG", "onRatingChanged: $it")
                        }

                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "wash services details", fontSize = 16.sp, fontWeight = FontWeight.W400)
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = appcolr,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 36.dp)
                            .height(50.dp),
                        onClick = {



                        }
                    ) {
                        Text(text = "Submit")
                    }


                }
            }
        }
    )
}



fun getRatingText(rating: Int): String {
    return when (rating) {
        1 -> "Very Poor"
        2 -> "Poor"
        3 -> "Average"
        4 -> "Good"
        5 -> "Excellent"
        else -> ""
    }
}


// Box(
//                        contentAlignment = Alignment.Center,
//                        modifier = Modifier
//                            .size(120.dp) // Total size of the profile picture with borders
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.washtracking), // Replace with your image
//                            contentDescription = "Profile Picture",
//                            modifier = Modifier
//                                .size(100.dp)
//                                .clip(CircleShape),
//                            contentScale = ContentScale.Crop
//                        )
//
//                        // Image Upload Icon
//                        Icon(
//                            imageVector = Icons.Default.Add,
//                            contentDescription = "Upload Icon",
//                            tint = Color.White,
//                            modifier = Modifier
//                                .align(Alignment.BottomEnd) // Place the icon at the bottom right
//                                .offset(
//                                    x = 0.dp,
//                                    y = (0).dp
//                                ) // Slight offset to appear outside the blue border
//                                .background(
//                                    color = appcolr,
//                                    shape = CircleShape
//                                )
//                                .padding(8.dp)
//                                .size(12.dp)
//                        )
//                    }