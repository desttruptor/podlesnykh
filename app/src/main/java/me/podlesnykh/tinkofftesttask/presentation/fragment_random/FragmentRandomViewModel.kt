package me.podlesnykh.tinkofftesttask.presentation.fragment_random

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.podlesnykh.tinkofftesttask.network.NetworkResponse
import me.podlesnykh.tinkofftesttask.network.Repository
import me.podlesnykh.tinkofftesttask.network.pojo.ErrorDialog
import me.podlesnykh.tinkofftesttask.presentation.models.Post
import java.util.*

class FragmentRandomViewModel : ViewModel() {

    private var isLoading = false

    private val stackForward: Stack<Post> = Stack()
    private val stackBackward: Stack<Post> = Stack()

    private val _mutableCurrentPost = MutableLiveData<Post>()
    val mutableCurrentPost: LiveData<Post> get() = _mutableCurrentPost

    private val _mutableBackButtonState = MutableLiveData(false)
    val mutableBackButtonState: LiveData<Boolean> get() = _mutableBackButtonState

    private val _mutableErrorDialog = MutableLiveData<ErrorDialog>()
    val mutableErrorDialog: LiveData<ErrorDialog> get() = _mutableErrorDialog

    private val repository by lazy { Repository() }

    fun loadInitialPost() {
        getRandomPost()
    }

    private fun getRandomPost() {
        viewModelScope.launch {
            isLoading = true
            when (val result = repository.getRandomPost()) {
                is NetworkResponse.SuccessRandomPost -> {
                    val post = result.data
                    _mutableCurrentPost.value = post
                }
                is NetworkResponse.NetworkError -> {
                    _mutableErrorDialog.value = ErrorDialog("Error")
                }
            }
            isLoading = false
        }
    }

    fun onClickBntBackward() {
        val post = stackForward.pop()
        stackBackward.push(post)
        _mutableCurrentPost.value = post
        if (stackForward.empty()) _mutableBackButtonState.value = false
    }

    fun onClickBtnForward() {
        if (isLoading) return
        if (stackBackward.empty()) {
            stackForward.push(_mutableCurrentPost.value)
            getRandomPost()
            _mutableBackButtonState.value = true
        } else {
            val post = stackBackward.pop()
            _mutableCurrentPost.value = post
            stackForward.push(post)
            _mutableBackButtonState.value = true
        }
    }

    fun onClickRetryButton() {
        getRandomPost()
    }

}