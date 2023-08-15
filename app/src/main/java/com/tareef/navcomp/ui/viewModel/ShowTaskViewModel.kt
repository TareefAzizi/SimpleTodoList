package com.tareef.navcomp.ui.viewModel

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tareef.navcomp.MyApplication
import com.tareef.navcomp.data.model.Task
import com.tareef.navcomp.repository.TaskRepository

class ShowTaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {
    val task: MutableLiveData<Task> = MutableLiveData()

    private var id: Int = -1

    fun init(id: Int) {
        this.id = id
        val res = repository.getTask(id)
        res?.let {
            task.value = it
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val  myRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).repo
                ShowTaskViewModel(
                    myRepository,
                )
            }
        }
    }
}