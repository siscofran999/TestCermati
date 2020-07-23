package com.siscofran.testcermati.ui

import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.siscofran.testcermati.R
import com.siscofran.testcermati.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    lateinit var viewModel: MainViewModel
    var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(MainViewModel::class.java)

        setSupportActionBar(toolbar)

        edt_search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                viewModel.getGithubUsers(s.toString())
            }

        })

        viewModel.getUser().observe(this, Observer {
            when {
                it == null -> {
                    rv.visibility = View.GONE
                    img.visibility = View.GONE
                    Toast.makeText(application, "Maaf, sudah mencapai limit API", Toast.LENGTH_SHORT).show()
                }
                it.size != 0 -> {
                    rv.visibility = View.VISIBLE
                    img.visibility = View.GONE
                    rv.layoutManager = LinearLayoutManager(this@MainActivity)
                    mainAdapter = MainAdapter(it)
                    rv.adapter = mainAdapter
                    mainAdapter?.notifyDataSetChanged()
                }
                else -> {
                    rv.visibility = View.GONE
                    img.visibility = View.GONE
                    Toast.makeText(application, "Maaf, data tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
        })

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val countItem = linearLayoutManager.itemCount
                val lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition
                if(isLastPosition){
                    viewModel.loadMore(edt_search.text.toString())
                }
            }
        })

        viewModel.getLoadMore().observe(this, Observer {
            when {
                it == null -> {
                    rv.visibility = View.GONE
                    img.visibility = View.GONE
                    Toast.makeText(application, "Maaf, sudah mencapai limit API", Toast.LENGTH_SHORT).show()
                }
                it.size != 0 -> {
                    rv.visibility = View.VISIBLE
                    img.visibility = View.GONE
                    mainAdapter?.refreshAdapter(it)
                }
                else -> {
                    img.visibility = View.GONE
                    rv.visibility = View.GONE
                    Toast.makeText(application, "Maaf, data tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getNetwork().observe(this, Observer {
            if(!it){
                rv.visibility = View.GONE
                img.visibility = View.VISIBLE
                Toast.makeText(application, "Maaf, tidak ada koneksi", Toast.LENGTH_SHORT).show()
            }else{
                if(edt_search.text.isNotEmpty()){
                    viewModel.getGithubUsers(edt_search.text.toString())
                }
            }
        })
    }
}