package com.example.eigenes_projekt_api_rv_mvvm.data.model

data class Current(
    val cloudcover: Int,
    val feelslike: Int,
    val humidity: Int,
    val is_day: String,
    val observation_time: String,
    val precip: Int,
    val pressure: Int,
    val temperature: Int,
    val uv_index: Int,
    val visibility: Int,
    val weather_code: Int,
    val weather_descriptions: List<Wetter>,
    val weather_icons: List<String>,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_speed: Int
)

data class Location(
    val country: String,
    val lat: String,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: String,
    val name: String,
    val region: String,
    val timezone_id: String,
    val utc_offset: String
)

data class Request(
    val language: String,
    val query: String,
    val type: String,
    val unit: String
)

data class Wetter(
    val current: Current,
    val location: String,
    val request: Request
)

