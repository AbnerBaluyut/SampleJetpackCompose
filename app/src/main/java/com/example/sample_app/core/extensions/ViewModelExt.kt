package com.example.sample_app.core.extensions

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_app.core.util.EventBus
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch

fun ViewModel.sendEvent(event: Any) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}

fun ViewModel.handler(r: Runnable, long: Long = 2000) {
    Handler(Looper.getMainLooper()).postDelayed(r, long)
}
