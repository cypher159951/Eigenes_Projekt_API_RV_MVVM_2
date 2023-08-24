package com.example.eigenes_projekt_api_rv_mvvm.data.remote

import com.example.eigenes_projekt_api_rv_mvvm.data.model.Wetter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "http://api.weatherstack.com/"


// Moshi konvertiert Serverantworten in Kotlin Objekte
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit übernimmt die Kommunikation und übersetzt die Antwort
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Das Interface bestimmt, wie mit dem Server kommuniziert wird
interface WetterApiService {

    /**
     * Diese Funktion spezifiziert die URL uns holt die Liste an Informationen
     */
    @GET("current?access_key=7467010219284b66a359a022888fefc2&query{city}")
    suspend fun getWetter(@Path("city")city: String): Wetter


}

// Das Objekt dient als Zugangspunkt für den Rest der App und stellt den API Service zur Verfügung
object WetterApi {
    val retrofitService: WetterApiService by lazy { retrofit.create(WetterApiService::class.java) }
}