package com.tareef.navcomp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.tareef.navcomp.MyApplication
import com.tareef.navcomp.data.model.Color
import com.tareef.navcomp.data.model.Task
import com.tareef.navcomp.repository.TaskRepository
import com.tareef.navcomp.ui.fragments.AddTodoFragment

class AddTodoViewModel(
    private val repo:TaskRepository
): ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData()
    val desc: MutableLiveData<String> = MutableLiveData()
    val finished: MutableLiveData<Boolean> = MutableLiveData( false)
    val error: MutableLiveData<String> = MutableLiveData("")
    val color: MutableLiveData<Color> = MutableLiveData()

    fun addTask(){
        if(title.value != null || desc.value != null && color.value != null){
            repo.addTask(
                Task(title = title.value!!, desc = desc.value!!, color = color.value!!)
            )
            finished.value = true
        } else{
            error.value = "Title and Desc can not be null"
        }
    }

fun onRedClicked(){
    color.value = Color.RED
}

    fun onGreenClicked(){
        color.value = Color.GREEN
    }

    fun onBlueClicked(){
        color.value = Color.BLUE
    }
    companion object{
        val Factory:ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
//                Get the application object from extras
            val application = checkNotNull(extras[APPLICATION_KEY])
//                create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return AddTodoViewModel(
                    (application as MyApplication).repo,
                ) as T
            }
        }
    }
}