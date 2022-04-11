package com.beok.ktorsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beok.ktorsample.data.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        println()
    }

    fun fetchUserRepository(userName: String) = viewModelScope.launch(coroutineExceptionHandler) {
        repository.fetchUserRepository(userName)
    }
}
