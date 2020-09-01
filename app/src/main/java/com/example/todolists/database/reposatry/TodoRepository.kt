package com.example.todolists.database.reposatry

import androidx.lifecycle.LiveData
import com.example.todolists.database.ToDoDAO
import com.example.todolists.database.model.TodoData

class TodoRepository (private val toDoDao: ToDoDAO) {

    val getAllData:LiveData<List<TodoData>> =toDoDao.getAllDatas()

//    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

//Insert
    suspend fun insertData(toDoData: TodoData){
        toDoDao.insertData(toDoData)
    }

    //Update
    suspend fun updateData(toDoData: TodoData)
    {
        toDoDao.updateData(toDoData)
    }
    //delete
    suspend fun deleteData(toDoData: TodoData)
    {
        toDoDao.deleteData(toDoData)
    }


}
