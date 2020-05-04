package com.wojiaowanghaha.learnkotlin.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.wojiaowanghaha.learnkotlin.R
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        btn_plus.setOnClickListener {
            viewModel.count++
            refreshCount()
        }
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
    }

    override fun onStop() {
        super.onStop()
        Log.e("ViewModelActivity","onStop")
    }
}
