package com.example.learning_practice.navigation_activity_module.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.learning_practice.DiceConfiguration
import com.example.learning_practice.R
import com.example.learning_practice.databinding.FragmentFirstTwoDiceBinding


class FirstTwoDice : Fragment()
{
    private var diceConf: DiceConfiguration = DiceConfiguration()
    private lateinit var binding: FragmentFirstTwoDiceBinding
     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first_two_dice,container,false)

//        binding.diceImage.setOnClickListener {
//            val img = when ((0..5).random())
//            {
//                0 -> R.drawable.dice_1
//                1 -> R.drawable.dice_2
//                2 -> R.drawable.dice_3
//                3 -> R.drawable.dice_4
//                4 -> R.drawable.dice_5
//                else -> R.drawable.dice_6
//            }
//            binding.diceImage.setImageResource(img)
//        }
         binding.change1.setOnClickListener {
             diceConf.changeDiceValue(arrayOf(
                (0..5).random(),
                (0..5).random(),
                (0..5).random(),
                (0..5).random(),
                (0..5).random(),
                (0..5).random()
            ))
//             diceConf.changeLiveDiceValue(arrayOf(
//                 (0..5).random(),
//                 (0..5).random(),
//                 (0..5).random(),
//                 (0..5).random(),
//                 (0..5).random(),
//                 (0..5).random()
//             ))

             val id = it.findNavController().currentDestination?.id
             it.findNavController().popBackStack(id!!,true)
             it.findNavController().navigate(id)
         }

  //       diceConf.testLiveDiceFace.observe(viewLifecycleOwner, Observer { data -> })
//         diceConf.testLiveDiceFace.observe(viewLifecycleOwner) { imgdata ->
//             val img = when (imgdata[0].absoluteValue) {
//                 0 -> R.drawable.dice_1
//                 1 -> R.drawable.dice_2
//                 2 -> R.drawable.dice_3
//                 3 -> R.drawable.dice_4
//                 4 -> R.drawable.dice_5
//                 else -> R.drawable.dice_6
//             }
//             binding.diceImage.setImageResource(img)
//         }

         binding.next1.setOnClickListener {
//             childFragmentManager.commit {
//                 setReorderingAllowed(true)
//                 //add<SecondTwoDice>(R.id.my_nav_host)
//                 add<SecondTwoDice>(R.id.frame1)
//             }
             it.findNavController().navigate(R.id.action_firstTwoDice_to_secondTwoDice)
         }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        diceConf = ViewModelProvider(requireActivity()).get(DiceConfiguration::class.java)
        lateinit var i: Array<Int>

        val img = when (diceConf.getDiceValue()[0])
        {
            0 -> R.drawable.dice_1
            1 -> R.drawable.dice_2
            2 -> R.drawable.dice_3
            3 -> R.drawable.dice_4
            4 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        binding.diceImage.setImageResource(img)
        val img2 = when (diceConf.getDiceValue()[1])
        {
            0 -> R.drawable.dice_1
            1 -> R.drawable.dice_2
            2 -> R.drawable.dice_3
            3 -> R.drawable.dice_4
            4 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        binding.diceImage2.setImageResource(img2)

    }
}
