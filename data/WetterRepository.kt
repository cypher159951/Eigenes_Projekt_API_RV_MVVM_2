package com.example.eigenes_projekt_api_rv_mvvm.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eigenes_projekt_api_rv_mvvm.data.model.Wetter
import com.example.eigenes_projekt_api_rv_mvvm.data.remote.WetterApi
import java.lang.Exception

const val TAG = "WetterRepository"

class WetterRepository(private val api: WetterApi) {

    private val _wetter = MutableLiveData<Wetter>()
    val wetter: LiveData<Wetter>
        get() = _wetter


    suspend fun getWetter() {
        try {
            _wetter.value = api.retrofitService.getWetter()
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }
    }
}