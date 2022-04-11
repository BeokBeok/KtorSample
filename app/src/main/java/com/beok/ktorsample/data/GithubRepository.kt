package com.beok.ktorsample.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubRepository(
    private val service: GithubService
) {

    suspend fun fetchUserRepository(userName: String) = withContext(Dispatchers.IO) {
        service.fetchRepositoryList(userName)
    }
}
