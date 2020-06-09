package com.example.androidkotlintaskc.adapterFacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlintaskc.R
import com.example.androidkotlintaskc.network.model.FactOfCat
import kotlinx.android.synthetic.main.item.view.*

class FactAdapter(): RecyclerView.Adapter<FactAdapter.FactHolder>() {

    private val list = mutableListOf<FactOfCat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
//        val inflater = LayoutInflater.from(parent.context)
        return FactHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
        val fact: FactOfCat = list[position]
        holder.bind(fact)
    }

    fun update(listServer: List<FactOfCat>){
        list.clear()
        list.addAll(listServer)
        notifyDataSetChanged()
    }


    class FactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fact: FactOfCat){
            itemView.text_of_fact.text = fact.text
        }
    }
}