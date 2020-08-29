package com.example.todolists.fragments.List

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolists.R
import com.example.todolists.database.model.Priority
import com.example.todolists.database.model.TodoData
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    var dataList = emptyList<TodoData>()
    class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view=   LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.title_txt.text=dataList.get(position).title
        holder.itemView.description_txt.text=dataList.get(position).descriptionn

        when(dataList[position].priority)
        {
            Priority.HIGH->holder.itemView.priority_indicator
                .setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.red))

            Priority.MEDIUM->holder.itemView.priority_indicator
                .setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.yellow))

            Priority.LOW->holder.itemView.priority_indicator
                .setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.green))
        }

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