/*
* 1 What is anotation
* 2 What is android X
* 3 Differernce between Tuype convertors and type convertor
* 4 What is abstract class and method when to use
* 5 What is companion objct
* 6 What is suspend
* 7 What is volatile
* 8 What is synchronized
* 9 What happens in this class
* */

package com.example.todolists.database

import android.content.Context
import androidx.room.*
import com.example.todolists.database.model.TodoData

@Database(entities = [TodoData::class], version = 1, exportSchema = false)
@TypeConverters(Convertor::class)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDAO

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}