package com.example.sample_app.core.extensions

fun String.toCapitalize(): String {
    return this.replaceFirstChar { char ->
        if (char.isLowerCase()) char.titlecase() else char.toString()
    }
}