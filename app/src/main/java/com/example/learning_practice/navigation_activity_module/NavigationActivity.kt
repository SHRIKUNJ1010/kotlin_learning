package com.example.learning_practice.navigation_activity_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.learning_practice.DiceConfiguration
import com.example.learning_practice.DiceData
import com.example.learning_practice.R
import com.example.learning_practice.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityNavigationBinding
    private  var diceConf: DiceConfiguration = DiceConfiguration()

    private  val diceData: DiceData = DiceData()


    lateinit var  rolledDiceNumber: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation)
//        diceConf = ViewModelProvider(this).get(DiceConfiguration::class.java)
//        binding.diceData = diceData
//
//        rolledDiceNumber = arrayOf(
//            1,1,1,1,1,1
//        )
    }
}
