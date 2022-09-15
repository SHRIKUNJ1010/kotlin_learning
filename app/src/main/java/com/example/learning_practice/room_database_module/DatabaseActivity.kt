package com.example.learning_practice.room_database_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.learning_practice.R
import com.example.learning_practice.databinding.ActivityDatabaseBinding
import com.example.learning_practice.room_database_module.database.SleepDatabase

class DatabaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDatabaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_database)
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val sleepTrackerViewModel = ViewModelProvider(
            this, viewModelFactory).get(SleepTrackerViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.sleepTrackerViewModel = sleepTrackerViewModel
    }
}
