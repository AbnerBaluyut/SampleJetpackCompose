package com.example.sample_app.presentations.products.presentation.product_detail

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sample_app.core.data.models.Cart
import com.example.sample_app.core.data.models.Product
import com.example.sample_app.core.extensions.handler
import com.example.sample_app.core.extensions.sendEvent
import com.example.sample_app.core.extensions.toJson
import com.example.sample_app.core.util.Event
import com.example.sample_app.presentations.cart.domain.payload.AddToCartParam
import com.example.sample_app.presentations.cart.domain.usecases.AddToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(ProductDetailViewState())
    val state: StateFlow<ProductDetailViewState> get() = _state.asStateFlow()

    fun addToCart(product: Product?) {

        viewModelScope.launch {
            setIsLoading(isLoading = true)
            addToCartUseCase.execute(
                param = AddToCartParam(
                    userId = 1,
                    date = "2024-11-04T00:00:00.000Z",
                    products = if (product == null) emptyList() else listOf(
                        Cart.CartProduct(
                            id = product.id,
                            quantity = 1
                        )
                    )
                )
            ).onRight {
                sendEvent(Event.Toast(message = "Added to cart"))
                setIsLoading(isLoading = false)
            }.onLeft {
                sendEvent(Event.Toast(message = "Something went wrong"))
                setIsLoading(isLoading = false)
            }
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }
}