package com.tareef.navcomp.repository

import com.tareef.navcomp.data.model.Task

class TaskRepository {
    val tasks: MutableList<Task> = mutableListOf(
//        Task(title = "title1", desc ="desc1"),
//        Task(title = "title1", desc ="desc1"),
//        Task(title = "title1", desc ="desc1"),
    )

    fun getTask(): List<Task>{
        return tasks.toList()
    }

    fun addTask(task: Task){
        tasks.add(task)
    }

    fun deleteTask(task: Task){
        tasks.remove(task)
    }

}