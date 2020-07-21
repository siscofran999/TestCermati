package com.siscofran.testcermati.data

import com.siscofran.testcermati.data.model.Users
import io.reactivex.Single

interface ApiRepository {

    fun getDataUser(name: String, page: Int, limit: Int): Single<Users>
}