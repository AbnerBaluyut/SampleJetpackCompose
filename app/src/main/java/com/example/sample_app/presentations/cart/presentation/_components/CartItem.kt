package com.example.sample_app.presentations.cart.presentation._components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sample_app.R
import com.example.sample_app.core.extensions.toNumberFormat
import com.example.sample_app.core.ui.theme.Purple30

@Composable
fun CartItem(title: String) {
    var quantity by remember { mutableIntStateOf(1) }

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .padding(8.dp)
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            content = {
                val imageString = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg"
                AsyncImage(
                    model = imageString,
                    contentDescription = imageString,
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    content = {
                        Text(
                            title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Purple30
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            (100.0 * quantity).toNumberFormat(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Purple30
                        )
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .shadow(10.dp, shape = CircleShape),
                            content = {
                                IconButton(
                                    onClick = {
                                        quantity++
                                    },
                                    colors = IconButtonColors(
                                        containerColor = Purple30,
                                        contentColor = Color.White,
                                        disabledContentColor = Color.White,
                                        disabledContainerColor = Color.Gray.copy(
                                            alpha = 0.5f
                                        )
                                    ),
                                    content = {
                                        Icon(
                                            modifier = Modifier.size(20.dp),
                                            imageVector = Icons.Rounded.Add,
                                            contentDescription = "Add",
                                        )
                                    }
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = quantity.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Purple30
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .shadow(10.dp, shape = CircleShape),
                            content = {
                                IconButton(
                                    enabled = quantity > 1,
                                    onClick = {
                                        quantity--
                                    },
                                    colors = IconButtonColors(
                                        containerColor = Color.White,
                                        contentColor = Purple30,
                                        disabledContentColor = Color.White,
                                        disabledContainerColor = Color.Gray.copy(
                                            alpha = 0.5f
                                        )
                                    ),
                                    content = {
                                        Icon(
                                            modifier = Modifier.size(20.dp),
                                            painter = painterResource(R.drawable.remove),
                                            contentDescription = "Remove",
                                        )
                                    }
                                )
                            }
                        )
                    }
                )
            }
        )
    }
}