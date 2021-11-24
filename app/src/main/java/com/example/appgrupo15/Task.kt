package com.example.appgrupo15

data class Task (val task: String, val time: String, val place: String){
    override fun toString(): String {
        return task
    }
}