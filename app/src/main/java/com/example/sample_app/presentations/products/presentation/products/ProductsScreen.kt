package com.example.sample_app.presentations.products.presentation.products

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.sample_app.core.extensions.toJson
import com.example.sample_app.core.routes.Screen
import com.example.sample_app.core.util.screenWidth
import com.example.sample_app.presentations.products.presentation.products._components.BuildCategoryTabs
import com.example.sample_app.presentations.products.presentation.products._components.ItemProduct
import com.example.sample_app.presentations.products.presentation.products._components.LoadingDialog
import com.example.sample_app.presentations.products.presentation.products._components.ProductsAppBar
import kotlinx.coroutines.launch

@Composable
internal fun ProductsScreen(
    navController: NavController,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.categories.size })
    val coroutineScope = rememberCoroutineScope()

    fun scrollToPage(index: Int) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(
                page = index
            )
        }
    }

    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ProductsAppBar(
                title = "Dashboard"
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                if (state.categories.isNotEmpty()) {
                    BuildCategoryTabs(
                        modifier = Modifier.fillMaxWidth(),
                        items = state.categories,
                        selectedTabIndex = state.tabIndex,
                        onTabClick = { index ->
                            viewModel.updateTabIndex(index = index)
                            scrollToPage(index = index)
                        },
                    )
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 0.5.dp,
                        color = Color.Gray
                    )
                }
                LaunchedEffect(pagerState.currentPage) {
                    val currentPageIndex = pagerState.currentPage
                    viewModel.updateTabIndex(currentPageIndex)
                }
                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    state = pagerState,
                    pageContent =  { index ->
                        LazyVerticalStaggeredGrid(
                            modifier = Modifier
                                .fillMaxSize(),
                            columns = StaggeredGridCells.Fixed(screenWidth() / 200),
                            horizontalArrangement = Arrangement.Start,
                            contentPadding = PaddingValues(14.dp),
                            state = rememberLazyStaggeredGridState(),
                            content = {
                                items(
                                    items = state.products.filter { e ->
                                        e.category.contains(state.categories[index])
                                    },
                                    itemContent = { product ->
                                        ItemProduct(
                                            modifier = Modifier
                                                .padding(6.dp)
                                                .clickable {
                                                    navController.navigate(Screen.ProductDetail.createRoute(
                                                        id = product.id,
                                                        json = product.toJson()
                                                    ))
                                                },
                                            product = product
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