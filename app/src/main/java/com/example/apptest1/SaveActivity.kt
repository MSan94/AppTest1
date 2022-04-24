package com.example.apptest1

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.apptest1.databinding.ActivitySaveBinding

class SaveActivity : BaseActivity<ActivitySaveBinding>({ ActivitySaveBinding.inflate(it)}) {

    override fun isAnimation(): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref : SharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)

        binding.btSave.setOnClickListener {
            val name = binding.etName.text
            val email = binding.etEmail.text
            val age = binding.etAge.text
            if(name.isEmpty() || email.isEmpty() || age.isEmpty()){
                Toast.makeText(this,"다입력해",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val editor = pref.edit()
            Log.d("TestLog", "--- name : ${pref.getString("name","없음")} / email : $email / age : $age")
            editor.putString("name", name.toString())
            editor.putString("email", email.toString())
            editor.putString("age", age.toString())
            editor.commit()
            Log.d("TestLog", "--- name : ${pref.getString("name","없음")} / email : $email / age : $age")
            setResult(RESULT_OK)
            finish()
        }
    }



}