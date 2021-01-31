package me.podlesnykh.tinkofftesttask.presentation.fragment_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.podlesnykh.tinkofftesttask.presentation.models.Category

class FragmentCategoryViewModelFactory(private val category: Category) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FragmentCategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FragmentCategoryViewModel(category) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}