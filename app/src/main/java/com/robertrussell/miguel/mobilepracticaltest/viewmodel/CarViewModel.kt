package com.robertrussell.miguel.mobilepracticaltest.viewmodel

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertrussell.miguel.mobilepracticaltest.domain.model.CarInformation
import com.robertrussell.miguel.mobilepracticaltest.domain.model.Response
import com.robertrussell.miguel.mobilepracticaltest.domain.repository.CarInformationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CarViewModel(
    repository: CarInformationRepository,
    private val assetManager: AssetManager
) : ViewModel() {

    private var carInformationRepository: CarInformationRepository = repository

    /**
     * Response flow
     */
    private val _responseState =
        MutableStateFlow<Response<MutableList<CarInformation>>>(Response.Success(null))
    val responseState : StateFlow<Response<MutableList<CarInformation>>> = _responseState

    fun getAllCarInformation() {
        viewModelScope.launch {
            carInformationRepository.getAllCarInfo().collectLatest {
                _responseState.value = it
            }
        }
    }
}
