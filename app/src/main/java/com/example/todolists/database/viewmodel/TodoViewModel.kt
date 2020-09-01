package com.example.todolists.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolists.database.ToDoDatabase
import com.example.todolists.database.model.TodoData
import com.example.todolists.database.reposatry.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel (application:Application): AndroidViewModel(application) {
    private val todoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val repository: TodoRepository
    val getAllData: LiveData<List<TodoData>>

    init {
        repository = TodoRepository(todoDao)
        getAllData = repository.getAllData
    }

    fun insertData(toDoData: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }
}

