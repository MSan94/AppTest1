package com.example.apptest1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest1.adapter.UserAdapter
import com.example.apptest1.contract.SubContract
import com.example.apptest1.databinding.ActivitySubBinding
import com.example.apptest1.databinding.ActivitySubBinding.inflate
import com.example.apptest1.model.UserData
import com.example.apptest1.presenter.SubPresenter

class SubActivity : BaseActivity<ActivitySubBinding>({ inflate(it) }) , SubContract.View {


    override fun isAnimation(): Boolean = false

    private var paging = 1

    private val presneter by lazy{
        SubPresenter(this)
    }

    var adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presneter.getUsers(paging,1)

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter?.itemCount
                if(lastPosition + 1 == itemTotalCount){
                    paging++
                    presneter.getUsers(paging,2)
                }
            }
        })
    }

    override fun showProgress() {
        binding.recyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    override fun setUsers(users: List<UserData>) {
        adapter.setItem(users)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun addUsers(users: List<UserData>) {
        adapter.addItem(users)
    }

    override fun showAlert(message: String) {
        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
    }


}