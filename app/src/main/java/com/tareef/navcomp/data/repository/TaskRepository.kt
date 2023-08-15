package com.tareef.navcomp.repository

import com.tareef.navcomp.data.model.Task

class TaskRepository {
    private var counter = 0
    private val tasks: MutableMap<Int,Task> = mutableMapOf()

    fun getTasks(): List<Task> {
        return tasks.values.toList()
    }

    fun getTask(id: Int): Task? {
        return tasks[id]
    }

    fun addTask(task: Task) {
        counter++
        tasks[counter] = task.copy(id = counter)
    }

    fun deleteTask(id: Int) {
        tasks.remove(id)
    }

    fun updateTask(task: Task) {
        tasks[task.id] = task
    }

}