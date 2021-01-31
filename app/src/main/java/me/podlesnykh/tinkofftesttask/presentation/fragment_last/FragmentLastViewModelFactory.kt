package me.podlesnykh.tinkofftesttask.presentation.fragment_last

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.podlesnykh.tinkofftesttask.presentation.fragment_hot.FragmentHotViewModel
import me.podlesnykh.tinkofftesttask.presentation.models.Categories

class FragmentLastViewModelFactory(private val categories: Categories) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FragmentLastViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FragmentLastViewModel(categories) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}