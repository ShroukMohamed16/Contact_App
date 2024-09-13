package com.example.contactapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnContactItemClick {

    val contactsList:MutableList<ContactModel> = mutableListOf()

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicks()


    }

    private fun initClicks(){
        binding.saveBtn.setOnClickListener {
            if(validateInputs()) {
                contactsList.add(
                    ContactModel(
                        binding.nameEt.text.toString(),
                        binding.phoneEt.text.toString(), binding.descriptionEt.text.toString()
                    )
                )
                showContacts(contactsList)
            }
        }
    }

    private fun showContacts(contactsList: MutableList<ContactModel>) {
        val contactAdapters = ContactAdapters(contactsList, onContactItemClick = this)
        binding.contactsRv.adapter = contactAdapters
    }

    private fun validateInputs():Boolean{
       return listOf(
            validateName(),
            validatePhone()
        ).all { it }
    }

    private fun validateName():Boolean{
        if(binding.nameEt.text.isNullOrBlank()) {
            binding.nameEt.error = "Please Enter Contact Name "
            return false
        }else{
            if(binding.nameEt.text.toString().length < 3){
                binding.nameEt.error = "Invalid Name"
                return false
            }
        }
        return true

    }

    private fun validatePhone():Boolean{
        if(binding.phoneEt.text.isNullOrBlank()) {
            binding.phoneEt.error = "Please Enter Contact Name "
            return false
        }else{
            val regex = "^01[0125]\\d{8}$".toRegex()
            if(!regex.matches(binding.phoneEt.text.toString())){
                binding.phoneEt.error = "Invalid Phone"
                return false
            }
        }
        return true
    }

    override fun onContactItemClick(contactModel: ContactModel) {
        val intent = Intent(this,ContactDetialsActivity::class.java)
        intent.putExtra("name",contactModel.name)
        intent.putExtra("phone",contactModel.phone)
        intent.putExtra("description",contactModel.description)
        startActivity(intent)
    }

}