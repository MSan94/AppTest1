package com.example.apptest1.util

import com.example.apptest1.model.UserList
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/api/users")
    fun getUsers(
        @Query("page") page : String
    ) : Observable<UserList>

}