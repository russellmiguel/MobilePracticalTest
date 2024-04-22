package com.robertrussell.miguel.mobilepracticaltest.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.robertrussell.miguel.mobilepracticaltest.R
import com.robertrussell.miguel.mobilepracticaltest.domain.model.CarInformation

@Composable
fun CarListItem(info: CarInformation, isExpanded: Boolean, onExpand: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(colorResource(id = R.color.light_gray))
            .clickable {
                onExpand(!isExpanded)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .animateContentSize(),
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

        if (isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp, 10.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Column {
                    Text(
                        text = "Pros:",
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    for (item in info.pros) {
                        Row(
                            modifier = Modifier.padding(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (item != "") {
                                Icon(
                                    painterResource(id = R.drawable.bullet_icon),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.orange),
                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 5.dp),
                                    text = item,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }

                Column {
                    Text(
                        text = "Cons:",
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    for (item in info.cons) {
                        Row(
                            modifier = Modifier.padding(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (item != "") {
                                Icon(
                                    painterResource(id = R.drawable.bullet_icon),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.orange),
                                )
                                Text(
                                    modifier = Modifier.padding(horizontal = 5.dp),
                                    text = item,
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
