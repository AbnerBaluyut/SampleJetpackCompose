package com.example.sample_app.presentations.cart.presentation

import androidx.lifecycle.ViewModel
import com.example.sample_app.presentations.cart.domain.GetCartsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartsUseCase: GetCartsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CartViewState())
    val state: StateFlow<CartViewState> get() = _state.asStateFlow()

}