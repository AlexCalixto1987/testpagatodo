package com.example.myapppagatodo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapppagatodo.data.BankRepository
import com.example.myapppagatodo.data.model.Bank
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: BankRepository) : ViewModel() {

    private var banksLiveData: MutableLiveData<ArrayList<Bank>> = MutableLiveData()

    fun getBanksLiveData(): LiveData<ArrayList<Bank>> = banksLiveData

    fun getBankList() {
        viewModelScope.launch { banksLiveData.value=repository.getBanks() }
    }
}