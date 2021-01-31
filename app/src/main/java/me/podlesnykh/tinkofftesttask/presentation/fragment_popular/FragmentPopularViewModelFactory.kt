package me.podlesnykh.tinkofftesttask.presentation.fragment_popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.podlesnykh.tinkofftesttask.presentation.fragment_last.FragmentLastViewModel
import me.podlesnykh.tinkofftesttask.presentation.models.Categories

class FragmentPopularViewModelFactory(private val categories: Categories) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FragmentPopularViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FragmentPopularViewModel(categories) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}