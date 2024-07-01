package com.ammaryasser.tourating.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ammaryasser.tourating.data.Tourating
import com.ammaryasser.tourating.repository.TouratingRepository
import kotlinx.coroutines.launch


class DetailsScreenViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = TouratingRepository.getInstance(app.applicationContext)
    private val _tourating = MutableLiveData<Tourating>()

    val tourating: LiveData<Tourating> get() = _tourating


    fun fetchById(id: Int) {
        viewModelScope.launch {
            repo.getById(id).collect {
                _tourating.postValue(it)
            }
        }
    }


    /**
     * Factory
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                DetailsScreenViewModel(this[APPLICATION_KEY] as Application)
            }
        }
    }
}