package com.example.eigenes_projekt_api_rv_mvvm.data.remote

import com.example.eigenes_projekt_api_rv_mvvm.data.model.Current
import com.example.eigenes_projekt_api_rv_mvvm.data.model.Wetter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

 val BASE_URL = "https://api.weatherstack.com/"

 val API_Token = "7467010219284b66a359a022888fefc2"

val logging = HttpLoggingInterceptor().let {
    it.level = HttpLoggingInterceptor.Level.BODY
    it
}


private val client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(logging)
    .addInterceptor { chain ->
    val newRequest = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer $API_Token")
        .build()
    chain.proceed(newRequest)
}
    .build()



// Moshi konvertiert Serverantworten in Kotlin Objekte
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit übernimmt die Kommunikation und übersetzt die Antwort
private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Das Interface bestimmt, wie mit dem Server kommuniziert wird
interface WetterApiService {

    /**
     * Diese Funktion spezifiziert die URL uns holt die Liste an Informationen
     */
    @GET("current/?access_key = 7467010219284b66a359a022888fefc2&query=New%20York")
    suspend fun getWetter(): Wetter


}

// Das Objekt dient als Zugangspunkt für den Rest der App und stellt den API Service zur Verfügung
object WetterApi {
    val retrofitService: WetterApiService by lazy { retrofit.create(WetterApiService::class.java) }
}