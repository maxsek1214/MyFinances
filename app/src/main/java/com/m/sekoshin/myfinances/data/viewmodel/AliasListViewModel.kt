package com.m.sekoshin.myfinances.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.m.sekoshin.myfinances.data.repository.AliasRepository
import com.m.sekoshin.myfinances.data.room.entity.AliasEntity

class AliasListViewModel(private val repository: AliasRepository) : ViewModel() {
    val allAliases: LiveData<List<AliasEntity>> = repository.allAliases.asLiveData()
}

class AliasListViewModelFactory(private val repository: AliasRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AliasListViewModel::class.java)) {
            return AliasListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}