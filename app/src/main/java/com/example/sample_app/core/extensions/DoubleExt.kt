package com.example.sample_app.core.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.toNumberFormat(): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return currencyFormat.format(this)
}