package me.podlesnykh.tinkofftesttask.presentation.fragment_category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.podlesnykh.tinkofftesttask.network.NetworkResponse
import me.podlesnykh.tinkofftesttask.network.Repository
import me.podlesnykh.tinkofftesttask.network.pojo.ErrorDialog
import me.podlesnykh.tinkofftesttask.presentation.models.Category
import me.podlesnykh.tinkofftesttask.presentation.models.Post

class FragmentCategoryViewModel(private val category: Category) : ViewModel() {

    private val postsList = mutableListOf<Post>()
    private var userPosition = 0
    private var isLoading = false

    private val _mutableCurrentUserPosition = MutableLiveData(0)
    val mutableCurrentUserPosition: LiveData<Int> get() = _mutableCurrentUserPosition

    private val _mutableCurrentPost = MutableLiveData<Post>()
    val mutableCurrentPost: LiveData<Post> get() = _mutableCurrentPost

    private val _mutableErrorDialog = MutableLiveData<ErrorDialog>()
    val mutableErrorDialog: LiveData<ErrorDialog> get() = _mutableErrorDialog

    private val repository by lazy { Repository() }

    fun loadInitialPage() {
        getLatestPage(0)
    }

    private fun getLatestPage(page: Int) {
        viewModelScope.launch {
            isLoading = true
            when (val result = repository.getPostsByCategory(page, category)) {
                is NetworkResponse.Success -> {
                    postsList.addAll(result.data)
                    _mutableCurrentPost.value = postsList[userPosition]
                }
                is NetworkResponse.NetworkError -> {
                    _mutableErrorDialog.value = ErrorDialog("Error")
                }
            }
            isLoading = false
        }
    }

    fun onClickBtnForward() {
        if (isLoading) return
        userPosition++
        if (userPosition >= postsList.size) {
            /* One page contains 5 posts, so next page if we have, for example, 1 page
            *  will be page.size (5) / 5 (== 1) (remember that page numeration starts from 0).
            * */
            getLatestPage(postsList.size / 5)
        } else {
            _mutableCurrentPost.value = postsList[userPosition]
        }
        _mutableCurrentUserPosition.value = userPosition
    }

    fun onClickBntBackward() {
        if (userPosition-- < 0) {
            userPosition = 0
        }
        _mutableCurrentPost.value = postsList[userPosition]
        _mutableCurrentUserPosition.value = userPosition
    }

    fun onClickRetryButton() {
        getLatestPage(postsList.size / 5)
    }

}