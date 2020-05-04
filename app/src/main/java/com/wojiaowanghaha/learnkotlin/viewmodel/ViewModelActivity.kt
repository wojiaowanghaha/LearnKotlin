package com.wojiaowanghaha.learnkotlin.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.wojiaowanghaha.learnkotlin.R
import com.wojiaowanghaha.learnkotlin.lifecycle.MyObserver
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var sp :SharedPreferences
    private var COUNT_RESERVED = "count_reserved"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        lifecycle.addObserver(MyObserver())
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt(COUNT_RESERVED,0)
        viewModel = ViewModelProvider(this,MainViewModelFactory(countReserved)).
        get(MainViewModel::class.java)
        btn_plus.setOnClickListener {
            viewModel.count++
            refreshCount()
        }
        btnClear.setOnClickListener {
            viewModel.count = 0
            refreshCount()
        }
        refreshCount()
    }

    private fun refreshCount() {
        tv_info.text = viewModel.count.toString()
    }


    override fun onResume() {
        super.onResume()
        Log.e("ViewModelActivity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("ViewModelActivity","onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("ViewModelActivity","onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.e("ViewModelActivity","onStart")
        sp.edit{
            putInt(COUNT_RESERVED,viewModel.count)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.e("ViewModelActivity","onStop")
    }
}
