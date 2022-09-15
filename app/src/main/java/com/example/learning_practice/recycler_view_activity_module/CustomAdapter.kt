package com.example.learning_practice.recycler_view_activity_module

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.learning_practice.R
import com.example.learning_practice.databinding.RecyclerViewItemBinding
import com.example.learning_practice.generated.callback.OnClickListener

class CustomAdapter(private val onItemClickListener: ItemsViewModelListener) : ListAdapter<ItemsViewModel, CustomAdapter.ViewHolder>(ItemsViewModelDiffCall()) {
//class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val view = LayoutInflater.from(parent.context)
        //    .inflate(R.layout.recycler_view_item, parent,false)
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = getItem(position)

//        holder.itemView.setOnClickListener {
//            onItemClickListener.onClick(ItemsViewModel)
//        }
        //holder.dataViewModel = ItemsViewModel

        holder.bind(ItemsViewModel,onItemClickListener)

        //holder.listner = onItemClickListener.onClick(ItemsViewModel)


    }

    class ViewHolder(val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //var dataViewModel : DataViewModel? = binding.dataModel
        fun bind(dataModel: ItemsViewModel?,onItemClickListener: ItemsViewModelListener){
            binding.dataModel = dataModel
            binding.clickListener = onItemClickListener
        }
    }
}

class ItemsViewModelDiffCall : DiffUtil.ItemCallback<ItemsViewModel>() {
    override fun areContentsTheSame(oldItem: ItemsViewModel, newItem: ItemsViewModel): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areItemsTheSame(oldItem: ItemsViewModel, newItem: ItemsViewModel): Boolean {
        return oldItem == newItem
    }
}

class ItemsViewModelListener(val clickListener: (itemId: String) -> Unit) {
    fun onClick(textModel: ItemsViewModel) = clickListener(textModel.text)
}
