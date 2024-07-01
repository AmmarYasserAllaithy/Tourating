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
//    private val _loading = MutableLiveData<Boolean>()

    val touratingList: LiveData<List<Tourating>> get() = _touratingList
//    val loading: LiveData<Boolean> get() = _loading


    init {
        fetchData()
    }


    private fun fetchData() {
//        _loading.postValue(true)

        viewModelScope.launch {
            repo.getAll().collect {
                _touratingList.postValue(it)
//                _loading.postValue(false)
            }
        }
    }


    // todo: main functionality


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