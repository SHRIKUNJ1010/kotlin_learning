package com.example.learning_practice.recycler_view_activity_module

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

data class ItemsViewModel(val text: String)

class DataViewModel(application: Application, itemModel: ItemsViewModel) :
    AndroidViewModel(application) {
    var text = itemModel.text
}

class DataViewModelFactory(
    private var itemModel: ItemsViewModel, private var application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataViewModel::class.java)) {
            return DataViewModel(application, itemModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    override fun toString(): String {
        return "DataViewModelFactory(itemModel=$itemModel, application=$application)"
    }

}
