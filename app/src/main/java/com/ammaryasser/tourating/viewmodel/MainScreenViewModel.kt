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


class MainScreenViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = TouratingRepository.getInstance(app.applicationContext)
    private val _touratingList = MutableLiveData<List<Tourating>>()

    val touratingList: LiveData<List<Tourating>> get() = _touratingList


    init {
        fetchData()
    }


    private fun fetchData() {
        viewModelScope.launch {
            repo.getAll().collect {
                _touratingList.postValue(it)
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            repo.delete(id)
        }
    }


    /**
     * Factory
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainScreenViewModel(this[APPLICATION_KEY] as Application)
            }
        }
    }

}