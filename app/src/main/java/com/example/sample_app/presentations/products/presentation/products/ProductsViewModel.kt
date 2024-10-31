package com.example.sample_app.presentations.products.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_app.core.extensions.sendEvent
import com.example.sample_app.core.util.Event
import com.example.sample_app.presentations.products.domain.GetCategoriesUseCase
import com.example.sample_app.presentations.products.domain.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ProductsViewState())
    val state: StateFlow<ProductsViewState> get() = _state.asStateFlow()

    init {
        onInit()
    }

    fun onInit() {
        viewModelScope.launch {
            getCategories()
        }
    }

    private suspend fun getCategories() {
        setIsLoading(isLoading = true)
        getCategoriesUseCase.execute()
            .onRight { categories ->
                _state.update {
                    it.copy(
                        categories = categories
                    );
                }
                getProducts()
            }
            .onLeft { error ->
                setErrorMessage(error = error.error.message)
                sendEvent(Event.Toast(message = "Something Went Wrong"))
                setIsLoading(isLoading = false)
            }
    }

    private suspend fun getProducts() {

        getProductsUseCase.execute()
            .onRight { products ->
                _state.update {
                    it.copy(
                        products = products
                    )
                }
                delay(2000)
                setIsLoading(isLoading = false)
                setIsPullToRefresh(value = false)
            }
            .onLeft { error ->
                setErrorMessage(error = error.error.message)
                sendEvent(Event.Toast(message = "Something Went Wrong"))
                setIsLoading(isLoading = false)
                setIsPullToRefresh(value = false)
            }
    }

    private fun setErrorMessage(error: String) {
        _state.update {
            it.copy(
                error = error
            )
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

    fun setIsPullToRefresh(value: Boolean) {
        _state.update {
            it.copy(
                isPullToRefresh = value
            )
        }
    }
}
