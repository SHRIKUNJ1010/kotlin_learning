package com.example.learning_practice.room_database_module

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.*
import com.example.learning_practice.room_database_module.database.SleepDatabaseDao
import com.example.learning_practice.room_database_module.database.SleepNightEntity
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException



class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private val nights = database.getAllNights()

    val nightsString = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }

    private var tonight = MutableLiveData<SleepNightEntity?>()
    init{
        initializeTonight()
    }
    private fun initializeTonight() {
        viewModelScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }
    private suspend fun getTonightFromDatabase(): SleepNightEntity? {
        var night = database.getTonight()
        if (night?.endTimeMilli != night?.startTimeMilli) {
            night = null
        }
        return night
    }
    fun onStartTracking() {
        viewModelScope.launch{
            val newNight = SleepNightEntity()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun insert(night: SleepNightEntity) {
        database.insert(night)
    }

    fun onStopTracking() {
        viewModelScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
        }
    }

    private suspend fun update (night: SleepNightEntity) {
        database.update(night)
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
            tonight.value = null
        }
    }

    suspend fun clear() {
        database.clear()
    }
}

class SleepTrackerViewModelFactory(
    private val dataSource: SleepDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java))
            {
                return SleepTrackerViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
