package com.ammaryasser.tourating.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ammaryasser.tourating.data.Tourating
import com.ammaryasser.tourating.repository.TouratingRepository
import kotlinx.coroutines.launch


class FormScreenViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = TouratingRepository.getInstance(app.applicationContext)


    fun fetchById(id: Int) = repo.getById(id)

    fun insertOrUpdate(tourating: Tourating) {
        viewModelScope.launch {
            repo.insertOrUpdate(tourating)
        }
    }


    /**
     * Factory
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                FormScreenViewModel(this[APPLICATION_KEY] as Application)
            }
        }
    }
}