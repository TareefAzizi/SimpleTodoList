package com.tareef.navcomp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tareef.navcomp.databinding.ItemLayoutTaskBinding
import com.tareef.navcomp.data.model.Task

class TaskAdapter(
    var items: List<Task>,
    val onDeleteClicked:(Task) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTaskBinding.inflate(layoutInflater, parent, false)
        return TaskViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if(holder is TaskViewHolder){
            holder.binding.run{
                tvTitle.text = item.title
                tvDesc.text = item.title

                ivDelete.setOnClickListener{
                    onDeleteClicked(item)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setNewItems(items: List<Task>){
        this.items = items
        notifyDataSetChanged()
    }

    class TaskViewHolder(val binding: ItemLayoutTaskBinding): RecyclerView.ViewHolder(binding.root)

}

