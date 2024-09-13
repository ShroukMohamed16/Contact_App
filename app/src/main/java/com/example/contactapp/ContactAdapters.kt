package com.example.contactapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.databinding.ItemContactBinding

class ContactAdapters(val list:List<ContactModel>,val  onContactItemClick:OnContactItemClick):RecyclerView.Adapter<ContactAdapters.ContactViewHolder>(){

    inner class ContactViewHolder(val item:ItemContactBinding):RecyclerView.ViewHolder(item.root) {
        fun bind(model: ContactModel){
            item.apply {
                item.contactName.text = model.name
                item.contactPhone.text = model.phone
                item.contactCard.setOnClickListener {
                    onContactItemClick.onContactItemClick(model)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
       holder.bind(list[position])
    }
}