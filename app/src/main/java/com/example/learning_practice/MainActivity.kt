package com.example.learning_practice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.learning_practice.databinding.ActivityMainBinding
import com.example.learning_practice.fragment_manager_module.FragmentManagerActivity
import com.example.learning_practice.navigation_activity_module.NavigationActivity
import com.example.learning_practice.recycler_view_activity_module.RecyclerViewShowActivity
import com.example.learning_practice.room_database_module.DatabaseActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.navActivity.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }
        binding.fragManActivity.setOnClickListener {
            val intent = Intent(this, FragmentManagerActivity::class.java)
            startActivity(intent)
        }
        binding.databaseActivity.setOnClickListener {
            val intent = Intent(this, DatabaseActivity::class.java)
            startActivity(intent)
        }
        binding.recyclerViewActivity.setOnClickListener {
            val intent = Intent(this, RecyclerViewShowActivity::class.java)
            startActivity(intent)
        }
        binding.apiTestActivity.setOnClickListener {
            val intent = Intent( this, ApiTestRetrofit::class.java)
            startActivity(intent)
        }
    }
}
