package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter(val listOfItems: List<String>,
                      val longClickListenner: OnLongClickListenner):
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    interface OnLongClickListenner{
        fun onItemLongCliked(position: Int)
    }

   //Usualy involves inflating
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context=parent.context
       val inflater = LayoutInflater.from(context)
       // inflarter the customer layout
       val contactView =inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
       //return a new Holder instance
       return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Get the data model base in position

        val item= listOfItems.get(position)

        holder.textView.text=item

        // Get the data model based on position

    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    // Provide a direct reference to each of the views within a data item
// Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // store references to elements in your layout view
        val textView: TextView
        init{
            textView=itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener(){
                longClickListenner.onItemLongCliked(adapterPosition)
                true
            }
        }
    }
}