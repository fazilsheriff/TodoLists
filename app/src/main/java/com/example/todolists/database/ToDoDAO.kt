package com.example.todolists.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolists.database.model.TodoData

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllDatas() : LiveData<List<TodoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: TodoData)

}