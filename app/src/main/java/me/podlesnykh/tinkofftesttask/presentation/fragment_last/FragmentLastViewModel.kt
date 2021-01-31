package me.podlesnykh.tinkofftesttask.presentation.fragment_last

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.podlesnykh.tinkofftesttask.presentation.models.Categories
import me.podlesnykh.tinkofftesttask.presentation.models.Page

class FragmentLastViewModel(categories: Categories) : ViewModel() {

    private val _mutableMoviesListPage = MutableLiveData<List<Page>>()
    val mutableMoviesListPage: MutableLiveData<List<Page>> get() = _mutableMoviesListPage

}