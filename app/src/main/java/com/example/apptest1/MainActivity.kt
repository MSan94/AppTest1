package com.example.apptest1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.UiThread
import com.example.apptest1.contract.MainContract
import com.example.apptest1.databinding.ActivityMainBinding
import com.example.apptest1.presenter.MainPresenter

class MainActivity : BaseActivity<ActivityMainBinding>( {ActivityMainBinding.inflate(it)} )
, MainContract.View{


    override fun isAnimation(): Boolean = false

    private val presenter by lazy {
        MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("lifeCycleTest","-----MainOnCreate")
        super.onCreate(savedInstanceState)
        binding.btn1.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }
        binding.btn2.setOnClickListener {
            val intent = Intent(this,SaveActivity::class.java)
            startActivityForResult(intent,3000)
        }
        binding.btn3.setOnClickListener {
            val intent = Intent(this, ServiceActivity::class.java)
            startActivity(intent)
        }


        binding.apply {
            tvText.setOnClickListener {
                if(layoutWeb.visibility == View.GONE){
                    layoutWeb.visibility = View.VISIBLE
                    Log.d("TestWebview", "${webView.contentHeight}")
                }else{
                    layoutWeb.visibility = View.GONE
                }
            }
            val data = "<p>sgpijfijgipsdjgpijjasifjafjaisfjpasfjpiafjpiasjfijfipajsfpiafifjfiasjfiaf World</p>"
            webView.loadData(data , "text/html; charset=utf-8", "UTF-8" )
            webView.setBackgroundColor(Color.parseColor("#808080"))
            webView.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView, url: String) {
                    layoutWeb.visibility = View.VISIBLE
                    Log.d("TestWebview" , "view : ${view.contentHeight} / webview : ${view.contentHeight}")
                }
            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("TestLog", "값 : $requestCode / $resultCode")
        if(resultCode == RESULT_OK && requestCode == 3000){
            val pref = getSharedPreferences("UserInfo", MODE_PRIVATE)
            Log.d("TestLog", "----------- IF")
            binding.tvText.text = pref.getString("name","") + " " + pref.getString("email","") + " " + pref.getString("age","")
        }
    }
}