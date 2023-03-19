package com.example.myapppagatodo.data

import com.example.myapppagatodo.data.model.Bank
import com.example.myapppagatodo.data.remote.BankApiClient
import javax.inject.Inject

class BankRepository @Inject constructor() {
    private val api by lazy { BankApiClient().getApiService() }
    suspend fun getBanks():ArrayList<Bank> {
        return api.getBanks()
    }
}