package com.example.sample_app.presentations.cart.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_app.core.data.models.Product
import com.example.sample_app.core.extensions.sendEvent
import com.example.sample_app.core.util.Event
import com.example.sample_app.presentations.cart.domain.entities.CartEntity
import com.example.sample_app.presentations.cart.domain.usecases.GetCartsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartsUseCase: GetCartsUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(CartViewState())
    val state: StateFlow<CartViewState> get() = _state.asStateFlow()

    private val _products: ArrayList<Product> = ArrayList()

    init {
        getCarts()
    }

    fun addProducts(products: List<Product>) {
        _products.apply {
            clear()
            addAll(products)
        }
    }

    private fun getCarts() {
        viewModelScope.launch {
            setIsLoading(isLoading = true)
            getCartsUseCase.execute()
                .onRight { items ->

                    val getItemByUserId = items.first { it.userId == 1 }
                    val cartEntities: List<CartEntity> = getItemByUserId.products.map { item ->
                        val filterProduct = _products.firstOrNull { it.id == item.id }
                        CartEntity(
                            name = filterProduct?.title ?: "",
                            price = filterProduct?.price ?: 0.0,
                            image = filterProduct?.image ?: "",
                            quantity = item.quantity
                        )
                    }

                    _state.update {
                        it.copy(
                            items = cartEntities
                        )
                    }

                    delay(2000)
                    setIsLoading(isLoading = false)
                }.onLeft { error ->
                    setErrorMessage(error = error.error.message)
                    sendEvent(Event.Toast(message = "Something Went Wrong"))
                    setIsLoading(isLoading = false)
                }
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
}