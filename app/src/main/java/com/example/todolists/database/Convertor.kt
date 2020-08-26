package com.example.todolists.database

import androidx.room.TypeConverter
import com.example.todolists.database.model.Priority

class Convertor
{
    @TypeConverter
fun fromPriority(priority: Priority):String{
    return priority.name
}
    @TypeConverter
    fun toPriority(priority:String): Priority {
        return Priority.valueOf(priority)
    }



  /*  @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }*/
}