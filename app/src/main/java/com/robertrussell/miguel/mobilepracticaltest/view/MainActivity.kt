package com.robertrussell.miguel.mobilepracticaltest.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.robertrussell.miguel.mobilepracticaltest.R
import com.robertrussell.miguel.mobilepracticaltest.domain.model.CarInformation
import com.robertrussell.miguel.mobilepracticaltest.domain.model.Response
import com.robertrussell.miguel.mobilepracticaltest.ui.theme.MobilePracticalTestTheme
import com.robertrussell.miguel.mobilepracticaltest.viewmodel.CarViewModel
import com.robertrussell.miguel.mobilepracticaltest.viewmodel.CarViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var carViewModel: CarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val assetManager = applicationContext.assets
        carViewModel =
            ViewModelProvider(this, CarViewModelFactory(assetManager))[CarViewModel::class.java]

        setContent {
            MobilePracticalTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage(carViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(carViewModel: CarViewModel) {
    Column {
        LaunchedEffect(Unit) {
            carViewModel.getAllCarInformation()
        }

        val response = carViewModel.responseState.collectAsStateWithLifecycle()
        var items: MutableList<CarInformation> = mutableListOf()
        when (response.value) {
            is Response.Loading -> {

            }

            is Response.Success -> {
                val responseValue = response.value as Response.Success
                Log.i("HomePage-response", "Success: ${responseValue.data}")
                if (responseValue.data != null) {
                    items = responseValue.data
                }
            }

            is Response.Failure -> {
                val responseValue = response.value as Response.Failure
                Log.e("HomePage-response", "e: ${responseValue.e}")
            }
        }

        TopAppBar(
            title = {
                Text(text = "GUIDOMIA", fontWeight = FontWeight.SemiBold)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.orange),
                titleContentColor = White
            ),
            actions = {
                // RowScope here, so these icons will be placed horizontally
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = null,
                        tint = White,
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var expandedIndex by remember { mutableStateOf(0) }
            var maker by remember { mutableStateOf("") }
            var model by remember { mutableStateOf("") }

            /**
             * Tacoma poster
             */
            Box(modifier = with(Modifier) {
                fillMaxWidth(1f).paint(
                    painterResource(id = R.drawable.tacoma),
                    contentScale = ContentScale.FillWidth
                )
            }) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Tacoma 2021",
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 10.dp, 0.dp),
                        style = TextStyle(
                            color = White,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        text = "Get your's now",
                        modifier = Modifier
                            .padding(15.dp, 0.dp, 10.dp, 0.dp),
                        style = TextStyle(
                            color = White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            /**
             * Filters section
             */
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.dark_gray))
                        .padding(10.dp)
                ) {
                    Column {
                        Text(
                            text = "Filters",
                            modifier = Modifier
                                .padding(10.dp, 0.dp, 10.dp, 0.dp),
                            style = TextStyle(
                                color = White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )

                        TextField(
                            value = maker,
                            onValueChange = {
                                maker = it
                                expandedIndex = 0
                            },
                            label = { Text("Any maker") },
                            singleLine = true,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(10.dp)
                        )

                        TextField(
                            value = model,
                            onValueChange = {
                                model = it
                                expandedIndex = 0
                            },
                            label = { Text("Any model") },
                            singleLine = true,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(10.dp)
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                /**
                 * Filter items to be viewed.
                 */
                val cars: MutableList<CarInformation> = mutableListOf()
                for (car in items) {
                    if (car.maker?.contains(maker, ignoreCase = true) == true &&
                        car.model?.contains(model, ignoreCase = true) == true
                    ) {
                        cars.add(car)
                    }
                }

                /**
                 * Items for LazyColumn
                 */
                items(cars.size) { index ->
                    CarListItem(
                        info = cars[index],
                        isExpanded = index == expandedIndex,
                        onExpand = { expanded ->
                            expandedIndex = if (expanded) index else -1
                        }
                    )
                    Divider(
                        color = colorResource(id = R.color.orange),
                        modifier = Modifier
                            .padding(10.dp)
                            .height(3.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopMenuPreview() {
    lateinit var carViewModel: CarViewModel
    MobilePracticalTestTheme {
        HomePage(carViewModel)
    }
}
