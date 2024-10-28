package com.example.sample_app.presentations.products.presentation.products._components

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.sample_app.core.extensions.toCapitalize
import java.util.Locale

@Composable
fun BuildCategoryTabs(
    modifier: Modifier,
    items: List<String>,
    selectedTabIndex: Int,
    onTabClick: (index: Int) -> Unit
) {
    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        divider = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.dp) // No height for the divider
            )
        },
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .padding(4.dp)
                    .fillMaxHeight()
                    .background(
                        color = Color(0xFF1565C0),
                        shape = RoundedCornerShape(60) // Pill shape for indicator
                    )
            )
        },
        tabs = {
            items.forEachIndexed { index, tab ->
                Tab(
                    modifier = Modifier
                        .zIndex(1f)
                        .padding(
                            horizontal = 8.dp
                        )
                        .clip(RoundedCornerShape(60.dp))
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {}
                        ),
                    selected = selectedTabIndex == index,
                    onClick = {
                        onTabClick.invoke(index)
                    },
                    text = {
                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            text = tab.toCapitalize(),
                            color = if (selectedTabIndex == index) Color.White else Color.Black,
                            fontSize = 16.sp,
                            fontWeight = if (selectedTabIndex == index) FontWeight.SemiBold else FontWeight.Normal,
                        )
                    }
                )
            }
        }
    )
}