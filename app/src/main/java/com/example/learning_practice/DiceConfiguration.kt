package com.example.learning_practice
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class DiceConfiguration : ViewModel(){
    var diceFace = arrayOf(0,1,2,3,4,5)
    fun getDiceValue(): Array<Int> {
        return diceFace
    }

    fun changeDiceValue(value:Array<Int>) {
        for (i in value.indices)
        {
            diceFace[i] = value[i]
        }
    }
}
