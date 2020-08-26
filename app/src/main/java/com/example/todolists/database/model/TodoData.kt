package com.example.todolists.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolists.database.model.Priority

@Entity(tableName = "todo_table")
data class TodoData (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var priority: Priority,
    var descriptionn:String
)