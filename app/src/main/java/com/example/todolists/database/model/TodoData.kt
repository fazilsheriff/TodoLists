package com.example.todolists.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolists.database.model.Priority
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "todo_table")
data class TodoData (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var priority: Priority,
    var descriptionn:String
):Parcelable