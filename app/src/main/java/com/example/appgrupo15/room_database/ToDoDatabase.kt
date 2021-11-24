package com.example.appgrupo15.room_database

import androidx.room.*

@Database(
    entities = arrayOf(ToDo::class),
    version = 1
)

abstract class ToDoDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDAO
}