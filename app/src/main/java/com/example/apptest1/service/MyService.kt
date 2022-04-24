package com.example.apptest1.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.widget.Toast

class MyService : Service() {

    private val handler : Handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            Toast.makeText(applicationContext, "bbb", Toast.LENGTH_SHORT).show()
            setSendEmptyMessageAtTime()
        }
    }
    fun setSendEmptyMessageAtTime(){
        handler.sendEmptyMessageAtTime(0,3000)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Toast.makeText(this,"bbb",Toast.LENGTH_SHORT).show()
        handler.sendEmptyMessageDelayed(0,3000)
        return START_STICKY
    }

    override fun onDestroy() {
        handler.removeMessages(0)
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}