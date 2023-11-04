package com.asadullo.chatapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asadullo.chatapp.Models.MessageData
import com.asadullo.chatapp.R
import com.asadullo.chatapp.databinding.ItemRvBinding

class AdapterRv(var list: ArrayList<MessageData>) : RecyclerView.Adapter<AdapterRv.Vh>() {
    inner class Vh(var item: ItemRvBinding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(user: MessageData) {
            if (user.id == 0){
                item.txtMessage.text = user.message
            }else{
                item.txtMessage.setBackgroundResource(R.drawable.bac_message_2)
                item.txtMessage.text = user.message
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

}