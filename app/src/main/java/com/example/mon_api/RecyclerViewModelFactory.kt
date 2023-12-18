package com.example.mon_api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RecyclerViewModelFactory(private val repository: WidgetRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST", "JvmSuppressWildcards")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecyclerViewModel::class.java)) {
            return RecyclerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
