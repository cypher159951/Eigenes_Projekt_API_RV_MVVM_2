package com.example.eigenes_projekt_api_rv_mvvm.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eigenes_projekt_api_rv_mvvm.data.model.Wetter
import com.example.eigenes_projekt_api_rv_mvvm.data.remote.WetterApi
import java.lang.Exception

const val TAG = "WetterRepository"

class WetterRepository(private val api: WetterApi) {

    private val _wetter = MutableLiveData<MutableList<Wetter>>()
    val wetter: LiveData<MutableList<Wetter>>
        get() = _wetter


    suspend fun getWetter() {
        try {
            if (_wetter.value == null) _wetter.value = mutableListOf()
            _wetter.value?.add(api.retrofitService.getWetter())
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }
    }
}