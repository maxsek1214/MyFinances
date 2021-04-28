package com.m.sekoshin.myfinances.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.m.sekoshin.myfinances.data.repository.AccountTypeRepository
import com.m.sekoshin.myfinances.data.room.entity.AccountTypeEntity

class AccountTypeListViewModel(private val repository: AccountTypeRepository) : ViewModel() {
    val allAccountTypes: LiveData<List<AccountTypeEntity>> = repository.allAccountTypes.asLiveData()
}

class AccountTypeListViewModelFactory(private val repository: AccountTypeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountTypeListViewModel::class.java)) {
            return AccountTypeListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}