package com.example.learning_practice.recycler_view_activity_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learning_practice.R
import com.example.learning_practice.databinding.ActivityRecyclerViewBinding

class RecyclerViewShowActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view)

        val recyclerView = binding.recyclerview
        val recyclerView2 = binding.recyclerview2

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView2.layoutManager = GridLayoutManager(this, 2)


        val data = ArrayList<ItemsViewModel>()

        for (i in 1..20) {
            val st = "Item $i"
            /*val str = DataViewModelFactory(ItemsViewModel(st), application)
            val stra: DataViewModel = ViewModelProvider(this, str)[DataViewModel::class.java]*/
            data.add(ItemsViewModel(st))
            //str = DataViewModelFactory(ItemsViewModel(""),application)
            //data.add(ViewModelProvider.DataViewModelFactory(ItemsViewModel("Item " + i)))
        }

        val adapter = CustomAdapter(ItemsViewModelListener { itemId ->
            Toast.makeText(applicationContext, "${itemId}", Toast.LENGTH_SHORT).show()
        })
        adapter.submitList(data)

        recyclerView.adapter = adapter
        recyclerView2.adapter = adapter
        binding.setLifecycleOwner(this)
    }
}
