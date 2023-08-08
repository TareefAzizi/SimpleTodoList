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
   private val repo: TaskRepository
): ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val isEmpty:MutableLiveData<Boolean> = MutableLiveData(false)
    init {
     fetchTasks()
    }

    fun fetchTasks(){
        val res = repo.getTask()
        tasks.value= res
        isEmpty.value = res.isEmpty()
    }

    fun deleteTask(task:Task){
        repo.deleteTask(task)
        fetchTasks()
    }


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return HomeViewModel(
                    (application as MyApplication).repo,
                ) as T
            }
        }
    }
}

