package com.example.contactapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactapp.databinding.ActivityContactDetialsBinding
import com.example.contactapp.databinding.ItemContactBinding

class ContactDetialsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetialsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showData()

    }

    private fun showData() {
        binding.contactNameValue.text = intent.getStringExtra("name")
        binding.contactPhoneValue.text = intent.getStringExtra("phone")
        binding.contactDescValue.text = intent.getStringExtra("description")
    }
}