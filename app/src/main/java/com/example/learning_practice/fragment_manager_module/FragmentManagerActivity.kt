package com.example.learning_practice.fragment_manager_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.example.learning_practice.*
import com.example.learning_practice.databinding.ActivityFragmentManagerBinding
import com.example.learning_practice.fragment_manager_module.fragments.FirstTwoDiceFragMan
import com.example.learning_practice.fragment_manager_module.fragments.SecondTwoDiceFragMan
import com.example.learning_practice.fragment_manager_module.fragments.ThirdTwoDiceFragMan


class FragmentManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentManagerBinding
    private var diceConf: DiceConfiguration = DiceConfiguration()

    private val diceData: DiceData = DiceData()

    lateinit var rolledDiceNumber: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fragment_manager)
        diceConf = ViewModelProvider(this).get(DiceConfiguration::class.java)
        binding.diceData = diceData

        rolledDiceNumber = arrayOf(
            1,1,1,1,1,1
        )

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<FirstTwoDiceFragMan>(R.id.my_man_host)
        }

        var i = 1

        binding.nextManager.setOnClickListener {
            if(i == 1)
            {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<SecondTwoDiceFragMan>(R.id.my_man_host)
                }
                i = 2
            }else if (i == 2) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<ThirdTwoDiceFragMan>(R.id.my_man_host)
                }
                i = 3
            } else if (i == 3){
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<FirstTwoDiceFragMan>(R.id.my_man_host)
                }
                i = 1
            }
        }


        binding.prevManager.setOnClickListener {
            if(i == 1)
            {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<ThirdTwoDiceFragMan>(R.id.my_man_host)
                }
                i = 3
            }else if(i == 2)
            {

                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<FirstTwoDiceFragMan>(R.id.my_man_host)
                }
                i = 1
            }
            else if (i == 3) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<SecondTwoDiceFragMan>(R.id.my_man_host)
                }
                i = 2
            }
        }

        binding.roller.setOnClickListener {
            diceConf.changeDiceValue(arrayOf(
                (0..5).random(),
                (0..5).random(),
                (0..5).random(),
                (0..5).random(),
                (0..5).random(),
                (0..5).random()
            ))
            if(i == 1)
            {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<FirstTwoDiceFragMan>(R.id.my_man_host)
                }

            }else if (i == 2) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<SecondTwoDiceFragMan>(R.id.my_man_host)
                }
            } else if (i == 3){
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<ThirdTwoDiceFragMan>(R.id.my_man_host)
                }
            }
        }

    }
}
