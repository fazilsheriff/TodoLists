package com.example.todolists.fragments.List

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolists.R
import com.example.todolists.database.model.Priority
import com.example.todolists.database.model.TodoData
import com.example.todolists.databinding.RowLayoutBinding
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    var dataList = emptyList<TodoData>()
    class MyViewHolder (private val binding: RowLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(toDoData: TodoData){
            binding.todoData = toDoData
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(
            parent
        )
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)

    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData (todoData:List<TodoData>)
    {
        this.dataList=todoData
        notifyDataSetChanged()
    }




}