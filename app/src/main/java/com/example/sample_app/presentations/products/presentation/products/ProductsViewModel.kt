package com.example.sample_app.presentations.products.presentation.products

import android.util.Log
import androidx.compose.foundation.pager.rememberPagerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_app.core.extensions.sendEvent
import com.example.sample_app.core.util.Event
import com.example.sample_app.presentations.products.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
): ViewModel() {

    private val _state = MutableStateFlow(ProductsScreenState())
    val state: StateFlow<ProductsScreenState> get() = _state.asStateFlow()

    init {
        onInit()
    }

    private fun onInit() {
        viewModelScope.launch {
            getCategories()
        }
    }

    private suspend fun getCategories() {
        setIsLoading(isLoading = true)
        productsRepository.getCategories()
            .onRight { categories ->
                _state.update {
                    it.copy(
                        categories = categories
                    );
                }
                getProducts()
            }
            .onLeft { error ->
                _state.update {
                    it.copy(
                        error = error.error.message
                    )
                }
                sendEvent(Event.Toast(message = "Something Went Wrong"))
                setIsLoading(isLoading = false)
            }
    }

    private suspend fun getProducts() {

        productsRepository.getProducts()
            .onRight { products ->
                _state.update {
                    it.copy(
                        products = products
                    )
                }
                setIsLoading(isLoading = false)
            }
            .onLeft { error ->
                _state.update {
                    it.copy(
                        error = error.error.message
                    )
                }
                sendEvent(Event.Toast(message = "Something Went Wrong"))
                setIsLoading(isLoading = false)
            }
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }

    fun updateTabIndex(index: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    tabIndex = index
                )
            }
        }
    }
}
