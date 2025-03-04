package com.o9tech.mytestafertoneday.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.binayshaw7777.kotstep.model.LineDefault
import com.binayshaw7777.kotstep.model.StepDefaults
import com.binayshaw7777.kotstep.model.StepStyle
import com.binayshaw7777.kotstep.model.tabVerticalWithLabel
import com.binayshaw7777.kotstep.ui.vertical.VerticalStepper
import com.o9tech.mytestafertoneday.R
import com.o9tech.mytestafertoneday.data.model.HistoryModel.AllHistoyModel
import com.o9tech.mytestafertoneday.mainViewModel.HistoryViewModel
import com.o9tech.mytestafertoneday.ui.theme.appcolr
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HistoryScreen(navController: NavHostController, historyViewModel: HistoryViewModel) {
    val history = historyViewModel.history.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val tabTitles = listOf("Past Washes", "In Progress", "Scheduled")
    val pagerState = rememberPagerState {
        tabTitles.size
    }
    Scaffold (
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text("History")
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
                    .padding(it),
            ) {

               Column(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(color = Color.White)
               ) {
                   TabRow(
                       selectedTabIndex = pagerState.currentPage,
                       containerColor = Color.White,
                       modifier = Modifier.fillMaxWidth(),
                       indicator = { tabPositions ->
                           TabRowDefaults.Indicator(
                               Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                               color = appcolr // Replace with your desired color
                           )

//                           TabRowDefaults.Indicator(
//                               Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
//                           )
                       }
                   ) {
                       tabTitles.forEachIndexed { index, title ->
                           Tab(
                               selectedContentColor = appcolr,
                               unselectedContentColor = Color.Black,
                               selected = pagerState.currentPage == index,
                               onClick = {
                                   coroutineScope.launch {
                                       pagerState.animateScrollToPage(index)
                                   }
                               },
                               text = { Text(text = title) }
                           )
                       }
                   }
                   HorizontalPager(
                       state = pagerState,
                       modifier = Modifier.weight(1f)
                   ) { page ->
                       when (page) {
                           0 -> PastWashesContent(history)
                           1 -> InProgressContent()
                           2 -> ScheduledContent()
                       }
                   }
               }
            }
        }
    )
}



@Composable
fun PastWashesContent(history: State<AllHistoyModel?>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            history.value?.hydraorder?.let {
                items(it.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(5.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 8.dp)
                        ) {
                            // Header Row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "09 Nov, 5:35 PM", style = MaterialTheme.typography.bodyLarge)
                                Column {
                                    Text(text = it[index].vehicleType.basePriceInCents.toString(), style = MaterialTheme.typography.bodyLarge)
                                    Row {
                                        Text(text = "Paid by ${it[index].paymentMethod.title}", style = MaterialTheme.typography.bodySmall)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription ="circle",tint = appcolr ,modifier = Modifier.size(16.dp))
                                    }
                                }
                            }


                            Spacer(modifier = Modifier.height(8.dp))
                            VerticalStepper(
                                style = tabVerticalWithLabel(
                                    totalSteps = 2,
                                    stepStyle = StepStyle(
                                        colors = StepDefaults(
                                            doneLineColor = appcolr,
                                            checkMarkColor = Color.Transparent,
                                            currentContainerColor = appcolr,
                                            doneContainerColor = appcolr,
                                            todoContainerColor = appcolr,
                                            currentLineColor = appcolr,
                                            todoLineColor = appcolr

                                        ),

                                        iconSize = 4.dp,


                                        stepShape = CircleShape,
                                        lineStyle = LineDefault(
                                            lineThickness = 2.dp,
                                            linePaddingTop = 3.dp,
        //                                        linePaddingEnd = 3.dp
        //                                        thickness = 2.dp,
        //                                        color = appcolr
                                        ),
                                        stepSize = 10.dp,

                                        ),
                                    currentStep = 1,
                                    trailingLabels = listOf(
//                                        { Text("Sedan, LEX 1295 - 12") },
                                        { Text(it[index].location.address.toString()) },
                                        { Text(it[index].washPoint.address, overflow = TextOverflow.Ellipsis, maxLines = 1) }
                                    )
                                )
                            ) {
                                // Do something...
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Washer Name
                            Text(
//                                text = "Washer Name",
                                text = it[index].washer.fullName,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Rating Row
        //            RatingRow()
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Rating: 4.5", style = MaterialTheme.typography.bodyMedium)
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Details Button
                            TextButton(
                                onClick = {

                                },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("for more Details", color = appcolr,)
                                Spacer(modifier = Modifier.width(14.dp))
                                Icon(painter = painterResource(id = R.drawable.arrow_forward),
                                    contentDescription = "arrow_forward",
                                    tint = appcolr
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InProgressContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
//        Text("In Progress Content")
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(10) { index -> // Replace 10 with your dynamic list size
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    ) {
                        // Header Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "09 Nov, 5:35 PM", style = MaterialTheme.typography.bodyLarge)
                            Column {
                                Text(text = "PKR 750", style = MaterialTheme.typography.bodyLarge)
                                Row {
                                    Text(text = "Paid by card", style = MaterialTheme.typography.bodySmall)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription ="circle",tint = appcolr ,modifier = Modifier.size(16.dp))
                                }
                            }
                        }


                        Spacer(modifier = Modifier.height(8.dp))

                        // Stepper Component
//            StepperComponent(stepperData)
                        VerticalStepper(
                            style = tabVerticalWithLabel(
                                totalSteps = 2,
                                stepStyle = StepStyle(
                                    colors = StepDefaults(
                                        doneLineColor = appcolr,
                                        checkMarkColor = Color.Transparent,
//                                        todoContentColor = appcolr
                                        currentContainerColor = appcolr,
                                        doneContainerColor = appcolr,
                                        todoContainerColor = appcolr,
                                        currentLineColor = appcolr,
                                        todoLineColor = appcolr

                                    ),

                                    iconSize = 4.dp,


                                    stepShape = CircleShape,
                                    lineStyle = LineDefault(
                                        lineThickness = 2.dp,
                                        linePaddingTop = 3.dp,
//                                        linePaddingEnd = 3.dp
//                                        thickness = 2.dp,
//                                        color = appcolr
                                    ),
                                    stepSize = 10.dp,

                                    ),
                                currentStep = 1,
                                trailingLabels = listOf(
                                    { Text("Sedan, LEX 1295 - 12") },
                                    { Text("Complete location/address of washing point", overflow = TextOverflow.Ellipsis, maxLines = 1) }
                                )
                            )
                        ) {
                            // Do something...
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Washer Name
                        Text(
                            text = "Washer Name",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Rating Row
//            RatingRow()
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Rating: 4.5", style = MaterialTheme.typography.bodyMedium)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Details Button
                        TextButton(
                            onClick = {

                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("for more Details", color = appcolr,)
                            Spacer(modifier = Modifier.width(14.dp))
                            Icon(painter = painterResource(id = R.drawable.arrow_forward),
                                contentDescription = "arrow_forward",
                                tint = appcolr
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScheduledContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(10) { index -> // Replace 10 with your dynamic list size
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    ) {
                        // Header Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "09 Nov, 5:35 PM", style = MaterialTheme.typography.bodyLarge)
                         Column {
                             Text(text = "PKR 750", style = MaterialTheme.typography.bodyLarge)
                            Row {
                                Text(text = "Paid by card", style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(imageVector = Icons.Default.CheckCircle, contentDescription ="circle",tint = appcolr ,modifier = Modifier.size(16.dp))
                            }
                         }
                        }


                        Spacer(modifier = Modifier.height(8.dp))

                        // Stepper Component
//            StepperComponent(stepperData)
                        VerticalStepper(
                            style = tabVerticalWithLabel(
                                totalSteps = 2,
                                stepStyle = StepStyle(
                                    colors = StepDefaults(
                                        doneLineColor = appcolr,
                                        checkMarkColor = Color.Transparent,
//                                        todoContentColor = appcolr
                                        currentContainerColor = appcolr,
                                        doneContainerColor = appcolr,
                                        todoContainerColor = appcolr,
                                        currentLineColor = appcolr,
                                        todoLineColor = appcolr

                                    ),

                                  iconSize = 4.dp,


                                    stepShape = CircleShape,
                                    lineStyle = LineDefault(
                                        lineThickness = 2.dp,
                                        linePaddingTop = 3.dp,
//                                        linePaddingEnd = 3.dp
//                                        thickness = 2.dp,
//                                        color = appcolr
                                    ),
                                    stepSize = 10.dp,

                                ),
                                currentStep = 1,
                                trailingLabels = listOf(
                                    { Text("Sedan, LEX 1295 - 12") },
                                    { Text("Complete location/address of washing point", overflow = TextOverflow.Ellipsis, maxLines = 1) }
                                )
                            )
                        ) {
                            // Do something...
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Washer Name
                        Text(
                            text = "Washer Name",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Rating Row
//            RatingRow()
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Rating: 4.5", style = MaterialTheme.typography.bodyMedium)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Details Button
                        TextButton(
                            onClick = {

                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("for more Details", color = appcolr,)
                            Spacer(modifier = Modifier.width(14.dp))
                            Icon(painter = painterResource(id = R.drawable.arrow_forward),
                                contentDescription = "arrow_forward",
                                tint = appcolr
                            )
                        }
                    }
                }
            }
        }
    }
}


