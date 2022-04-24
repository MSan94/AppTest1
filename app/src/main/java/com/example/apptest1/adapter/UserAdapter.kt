package com.example.apptest1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apptest1.databinding.ItemUserBinding
import com.example.apptest1.model.UserData
import com.example.apptest1.model.UserList

class UserAdapter : RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    private var users : ArrayList<UserData> = ArrayList()

    inner class ItemViewHolder(private val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model : UserData){
            Glide.with(binding.ivAvatar.context)
                .load(model.avatar)
                .into(binding.ivAvatar)
            binding.tvEmail.text = model.email
            binding.tvName.text = model.first_name + " " + model.last_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun setItem(items : List<UserData>){
        users = items as ArrayList<UserData>
        this.notifyDataSetChanged()
    }

    fun addItem(items : List<UserData>){
        users.addAll(items)
        this.notifyDataSetChanged()
    }
}