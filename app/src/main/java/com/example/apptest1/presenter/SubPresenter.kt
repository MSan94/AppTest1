package com.example.apptest1.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.example.apptest1.contract.SubContract
import com.example.apptest1.util.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SubPresenter(private val view : SubContract.View) : SubContract.Presneter {

    val retrofit = RetrofitClient.instance

    @SuppressLint("CheckResult")
    override fun getUsers(page : Int, type : Int) {
        view.showProgress()
        retrofit.getUsers(page.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnEach { view.hideProgress() }
            .subscribe({
                if(it.data.isEmpty()){
                    view.showAlert("추가 데이터 없음")
                }else {
                    if (type == 2) {
                        view.addUsers(it.data)
                    } else {
                        view.setUsers(it.data)
                    }
                }
            },{
                if(it is Exception){
                    view.showAlert(it.message!!)
                }
            })

    }


}