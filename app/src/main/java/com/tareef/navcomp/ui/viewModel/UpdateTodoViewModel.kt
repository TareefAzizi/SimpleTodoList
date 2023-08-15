package com.tareef.navcomp.ui.viewModel

import com.tareef.navcomp.data.model.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tareef.navcomp.MyApplication
import com.tareef.navcomp.data.model.Color.*
import com.tareef.navcomp.data.model.Task
import com.tareef.navcomp.repository.TaskRepository

class UpdateTodoViewModel(
    private val repository: TaskRepository,
) : ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData()
    val desc: MutableLiveData<String> = MutableLiveData()
    val finished: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: MutableLiveData<String> = MutableLiveData("")
    private var id: Int = -1
    val color: MutableLiveData<Color> = MutableLiveData()

    fun init(id: Int) {
        this.id = id
        val res = repository.getTask(id)
        res?.let {
            title.value = it.title
            desc.value = it.desc
        }
    }

    fun updateTask() {
        if (title.value != null && desc.value != null && color.value != null) {
            repository.updateTask(
                Task(id = id, title = title.value!!, desc = desc.value!!, color = color.value!!)
            )
            finished.value = true
        } else if (title.value == null || desc.value == null) {
            error.value = "Title and Desc can not be null"
        } else {
            error.value = "Please select a color"
        }
    }

    fun onRedClicked() {
        color.value = Color.RED
    }

    fun onBlueClicked() {
        color.value = Color.BLUE
    }

    fun onGreenClicked() {
        color.value = Color.GREEN
    }


    fun deleteTask() {
        repository.deleteTask(id)
        finished.value = true
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).repo
                UpdateTodoViewModel(
                    myRepository,
                )
            }
        }
    }
}