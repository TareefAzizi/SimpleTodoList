package com.tareef.navcomp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tareef.navcomp.databinding.ItemLayoutTaskBinding
import com.tareef.navcomp.data.model.Task

class TaskAdapter(
    var items: List<Task>,
    val onItemClicked: (Task) -> Unit,
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

                llTask.setOnClickListener{
                    onItemClicked(item)
                }

                cvTask.setCardBackgroundColor(
                    ContextCompat.getColor(
                        root.context,
                        item.color.rgb
                    )
                )
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

