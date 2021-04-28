package com.m.sekoshin.myfinances.data.viewmodel

import androidx.lifecycle.*
import com.m.sekoshin.myfinances.data.repository.SourceRecipientRepository
import com.m.sekoshin.myfinances.data.room.entity.SourceRecipientEntity
import kotlinx.coroutines.launch

class SourceRecipientListViewModel(private val repository: SourceRecipientRepository) : ViewModel() {
    val allSourceRecipients: LiveData<List<SourceRecipientEntity>> = repository.allSourceRecipients.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(sourceRecipientEntity: SourceRecipientEntity) = viewModelScope.launch {
        repository.insert(sourceRecipientEntity)
    }
}

class SourceRecipientViewModelFactory(private val repository: SourceRecipientRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SourceRecipientListViewModel::class.java)) {
            return SourceRecipientListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}