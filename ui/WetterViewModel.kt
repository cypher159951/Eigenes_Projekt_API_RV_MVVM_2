package com.example.eigenes_projekt_api_rv_mvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eigenes_projekt_api_rv_mvvm.data.WetterRepository
import com.example.eigenes_projekt_api_rv_mvvm.data.model.Wetter
import com.example.eigenes_projekt_api_rv_mvvm.data.remote.WetterApi
import kotlinx.coroutines.launch

class WetterViewModel : ViewModel() {

    // private val savedWetterTitel: MutableList<String> = mutableListOf()

    private val repository = WetterRepository(WetterApi)
    var wetter: LiveData<List<Wetter>> = repository.wetter

    fun loadData(){
        viewModelScope.launch {
            repository.getWetter()
        }
    }

}