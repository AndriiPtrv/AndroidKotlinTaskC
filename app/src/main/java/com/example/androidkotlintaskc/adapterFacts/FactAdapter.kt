package com.example.androidkotlintaskc.adapterFacts

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlintaskc.R
import com.example.androidkotlintaskc.network.model.FactOfCat

class FactAdapter(): RecyclerView.Adapter<FactAdapter.FactHolder>() {

    private val list = mutableListOf<FactOfCat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FactHolder(inflater, parent)
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


    class FactHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item, parent, false)) {
        private var mText: TextView? = null
        init {
            mText = itemView.findViewById(R.id.text_of_fact)
        }
        fun bind(fact: FactOfCat){
            mText?.text = fact.text
        }
    }
}