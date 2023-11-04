package com.asadullo.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.asadullo.chatapp.Adapters.AdapterRv
import com.asadullo.chatapp.Models.MessageData
import com.asadullo.chatapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var list: ArrayList<MessageData>
    private lateinit var adapter:AdapterRv
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("my_message")
        list = ArrayList()
        adapter = AdapterRv(list)

        getSms()

        binding.btnSend.setOnClickListener {
            val text = binding.edyMessage.text.toString()
            reference.setValue(text)
            Toast.makeText(this, "Sent", Toast.LENGTH_SHORT).show()
        }

        binding.rv.adapter = adapter

    }
    fun getSms(){
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.add(MessageData(snapshot.value.toString(), 1))
                binding.rv.verticalScrollbarPosition = list.size-1
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}