package com.example.apptest1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.apptest1.databinding.ActivityBaseBinding

abstract class BaseActivity<T : ViewBinding>(val bindingFactory : (LayoutInflater) -> T) : AppCompatActivity() {

    private var _binding : T? = null
    val binding get() = _binding!!

    protected abstract fun isAnimation() : Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifeCycleTest","-----BaseOnCreate")
        init()
    }

    private fun init(){
        Log.d("lifeCycleTest","-----BaseInit")
        _binding = bindingFactory(layoutInflater)
        if(!isAnimation()){
            Toast.makeText(this,"애니메이션 없음" , Toast.LENGTH_SHORT).show()
        }
        setContentView(binding.root)
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    open fun showAlert(message : String){}

    open fun showError(code : String, Message : String){}

}