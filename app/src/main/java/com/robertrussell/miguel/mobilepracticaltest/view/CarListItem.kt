package com.robertrussell.miguel.mobilepracticaltest.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.robertrussell.miguel.mobilepracticaltest.R
import com.robertrussell.miguel.mobilepracticaltest.domain.model.CarInformation

@Composable
fun CarListCollapsedItem(info: CarInformation) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(colorResource(id = R.color.light_gray)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val image = when (info.maker) {
                "Land Rover" -> R.drawable.range_rover
                "Alpine" -> R.drawable.alpine_roadster
                "BMW" -> R.drawable.bmw_330i
                "Mercedes Benz" -> R.drawable.mercedez_benz
                else -> {
                    R.drawable.loading
                }
            }
            Image(
                painter = painterResource(id = image),
                contentDescription = info.model,
                Modifier
                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
                    .height(70.dp)
            )
            Column(
                modifier = Modifier
                    .padding(15.dp, 0.dp, 10.dp, 0.dp),
            ) {
                Text(
                    text = info.maker + " " + info.model,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Price: ${info.marketPrice}",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Row {
                    for (i in 1..info.rating) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null,
                            tint = colorResource(id = R.color.orange),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CarListItemPreview() {
    val info = CarInformation(
        id = 0,
        model = "Range Rover",
        maker = "Land Rover",
        marketPrice = 125000.0,
        customerPrice = 120000.0,
        rating = 3,
        cons = listOf("Bad direction"),
        pros = listOf(
            "You can go everywhere",
            "Good sound system"
        )
    )
    CarListCollapsedItem(info = info)
}

