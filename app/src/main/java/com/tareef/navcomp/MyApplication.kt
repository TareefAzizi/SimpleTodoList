package com.tareef.navcomp

import android.app.Application
import com.tareef.navcomp.repository.TaskRepository

class MyApplication:Application() {
    val repo = TaskRepository()
}