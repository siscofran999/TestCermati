package com.siscofran.testcermati.data

import com.siscofran.testcermati.data.model.Users
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    override fun getDataUser(name: String, page: Int, limit: Int): Single<Users>
            = apiService.getUsers(name, page, limit)

}