package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val viewModel: MyViewModel) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    inner class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view){
        fun setContents(pos:Int){
            val textView = view.findViewById<TextView>(R.id.textView)
            val textView2 = view.findViewById<TextView>(R.id.textView2)

            with(viewModel.items[pos]){
                textView.text = name
                textView2.text = address
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_layout, parent, false)
        val viewHolder = ViewHolder(view)

        view.setOnClickListener{
            viewModel.itemClickEvent.value = viewHolder.adapterPosition
        }

        view.setOnLongClickListener{
            viewModel.itemLongClick = viewHolder.adapterPosition
            false
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.setContents()
        val view = holder.itemView
        val text1 = view.findViewById<TextView>(R.id.textView)
        val text2 = view.findViewById<TextView>(R.id.textView2)

        text1.text = viewModel.items[position].name
        text2.text = viewModel.items[position].address
    }

    override fun getItemCount(): Int {
        return viewModel.items.size;
    }
}