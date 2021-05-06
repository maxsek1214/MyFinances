package com.m.sekoshin.myfinances.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.m.sekoshin.myfinances.data.repository.IssuerRepository
import com.m.sekoshin.myfinances.data.room.entity.IssuerEntity

class IssuerListViewModel(private val repository: IssuerRepository) : ViewModel() {
    val allIssuers: LiveData<List<IssuerEntity>> = repository.allIssuers.asLiveData()
}

class IssuerListViewModelFactory(private val repository: IssuerRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IssuerListViewModel::class.java)) {
            return IssuerListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}