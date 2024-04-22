package com.robertrussell.miguel.mobilepracticaltest.viewmodel

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robertrussell.miguel.mobilepracticaltest.domain.repository.CarInformationRepository

class CarViewModelFactory(private val assetManager: AssetManager) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarViewModel::class.java)) {
            return CarViewModel(CarInformationRepository(assetManager), assetManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}