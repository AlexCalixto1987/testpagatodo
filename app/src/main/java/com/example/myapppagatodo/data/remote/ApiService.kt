package com.example.myapppagatodo.data.remote

import com.example.myapppagatodo.data.model.Bank
import retrofit2.http.GET

interface ApiService {
    @GET("/catom/api/challenge/banks")
    suspend fun getBanks(): ArrayList<Bank>
}