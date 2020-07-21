package com.siscofran.testcermati.data

import com.siscofran.testcermati.data.model.Users
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getUsers(
        @Query("q") name: String,
        @Query("page") page : Int,
        @Query("per_page") limit : Int
    ): Single<Users>
}