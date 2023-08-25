package com.example.eigenes_projekt_api_rv_mvvm.data.model

data class Wetter(
    val current: Current?,
    val location: Location,
    val request: Request
)