package com.example.eigenes_projekt_api_rv_mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.eigenes_projekt_api_rv_mvvm.adapter.WetterAdapter
import com.example.eigenes_projekt_api_rv_mvvm.data.WetterRepository
import com.example.eigenes_projekt_api_rv_mvvm.data.model.Wetter
import com.example.eigenes_projekt_api_rv_mvvm.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val viewModel: WetterViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.loadData()

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.wetter.observe(viewLifecycleOwner){
            Log.d("Test_Wetter_API", "$it")
            binding.staedteRV.adapter = WetterAdapter(it, viewModel, requireContext())
        }
    }
}