package com.example.apptest1

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.apptest1.databinding.ActivityServiceBinding
import com.example.apptest1.service.MyService

class ServiceActivity : BaseActivity<ActivityServiceBinding>({ ActivityServiceBinding.inflate(it)}) {

    override fun isAnimation(): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnStart.setOnClickListener {
            clickStart()
        }
        binding.btnStop.setOnClickListener {
            clickStop()
        }
    }

    fun clickStart(){
        val intent = Intent(this, MyService::class.java)
        startService(intent)
    }

    fun clickStop(){
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }

}