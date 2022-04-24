package com.example.apptest1.contract

import com.example.apptest1.model.UserData
import com.example.apptest1.model.UserList

interface SubContract {

    interface View : BaseView{
        fun showProgress()
        fun hideProgress()
        fun setUsers(users : List<UserData>)
        fun addUsers(users : List<UserData>)
    }

    interface Presneter : BasePresenter{
        fun getUsers(page : Int, type : Int)
    }

}