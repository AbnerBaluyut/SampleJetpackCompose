package com.example.sample_app.core.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> List<T>.toJson(): String {
    val gson = Gson()
    val type = object : TypeToken<List<T>>() {}.type
    return gson.toJson(this, type)
}


inline fun <reified T> String.fromJsonToList(): List<T> {
    val gson = Gson()
    val type = object : TypeToken<List<T>>() {}.type
    return gson.fromJson(this, type) ?: emptyList()
}