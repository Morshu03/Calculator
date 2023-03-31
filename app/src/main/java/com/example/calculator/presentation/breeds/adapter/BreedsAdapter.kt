package com.example.calculator.presentation.breeds.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.presentation.breeds.RecyclerViewInterface



class BreedsAdapter(val recyclerViewInterface: RecyclerViewInterface) : RecyclerView.Adapter<BreedsAdapter.MyViewHolder>() {
    private var breedList: Set<String> = setOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_breed, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedTextView: TextView = itemView.findViewById(R.id.breedTextView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.breedTextView.text = breedList.toList()[position]
        holder.itemView.setOnClickListener {
            recyclerViewInterface.onItemClick(breedList.toList()[position])
        }
    }

    override fun getItemCount(): Int = breedList.size

    fun setList(newBreedList: Set<String>) {
        breedList = newBreedList
        notifyDataSetChanged()
    }
}