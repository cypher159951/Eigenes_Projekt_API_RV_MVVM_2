package com.example.eigenes_projekt_api_rv_mvvm.data.model

data class Request(
    val language: String,
    val query: String,
    val type: String,
    val unit: String
)