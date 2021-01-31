package me.podlesnykh.tinkofftesttask.presentation.fragment_hot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.podlesnykh.tinkofftesttask.presentation.models.Categories

class FragmentHotViewModelFactory(private val categories: Categories) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FragmentHotViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FragmentHotViewModel(categories) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}