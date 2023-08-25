package com.example.eigenes_projekt_api_rv_mvvm.data.remote

import android.util.Log
import com.example.eigenes_projekt_api_rv_mvvm.data.model.Wetter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.nio.charset.Charset

 val BASE_URL = "http://api.weatherstack.com/"
 val API_Token = "7467010219284b66a359a022888fefc2"

//val logging = HttpLoggingInterceptor().let {
//    it.level = HttpLoggingInterceptor.Level.BODY
//    it
//}


class RawJSONLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Only log if the response is JSON
        if (response.header("Content-Type")?.contains("application/json") == true) {
            val responseBody = response.body
            val source = responseBody!!.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body
            val buffer = source.buffer
            // Using UTF-8 as it's a common charset for JSON data
            var charset: Charset = Charsets.UTF_8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(Charsets.UTF_8) ?: Charsets.UTF_8
            }
            val json = buffer.clone().readString(charset)
            Log.d("JSONLOGRESPONSE","Received JSON response: $json")
        }
        return response
    }
}


private val client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(RawJSONLoggingInterceptor())
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
    @GET("current?access_key=7467010219284b66a359a022888fefc2&query=Cologne")
    suspend fun getWetter(): Wetter


}

// Das Objekt dient als Zugangspunkt für den Rest der App und stellt den API Service zur Verfügung
object WetterApi {
    val retrofitService: WetterApiService by lazy { retrofit.create(WetterApiService::class.java) }
}