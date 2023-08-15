package com.tareef.navcomp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tareef.navcomp.MyApplication
import com.tareef.navcomp.data.model.Task
import com.tareef.navcomp.repository.TaskRepository

class HomeViewModel(   
   private val repository: TaskRepository
): ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val isEmpty:MutableLiveData<Boolean> = MutableLiveData(false)
    init {
     fetchTasks()
    }

    fun fetchTasks(){
        val res = repository.getTasks()
        tasks.value= res
        isEmpty.value = res.isEmpty()
    }

    fun deleteTask(task:Task){
        repository.deleteTask(task.id)
        fetchTasks()
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as MyApplication).repo
                HomeViewModel(
                    myRepository,
                )
            }
        }
    }
}

