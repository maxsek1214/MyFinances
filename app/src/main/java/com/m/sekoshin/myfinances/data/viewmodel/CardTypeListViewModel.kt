package com.m.sekoshin.myfinances.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.m.sekoshin.myfinances.data.repository.CardTypeRepository
import com.m.sekoshin.myfinances.data.room.entity.CardTypeEntity

class CardTypeListViewModel(private val repository: CardTypeRepository) : ViewModel() {
    val allCardTypes: LiveData<List<CardTypeEntity>> = repository.allCardTypes.asLiveData()
}

class CardTypeListViewModelFactory(private val repository: CardTypeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardTypeListViewModel::class.java)) {
            return CardTypeListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}