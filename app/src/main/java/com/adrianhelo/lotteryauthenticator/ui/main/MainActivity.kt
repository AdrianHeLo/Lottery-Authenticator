package com.adrianhelo.lotteryauthenticator.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.adrianhelo.lotteryauthenticator.R
import com.adrianhelo.lotteryauthenticator.data.local.AppDatabase
import com.adrianhelo.lotteryauthenticator.data.local.LotteryDao

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.addLotteryBtn)
        var recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        var adapter = LotteryAdapter{
            viewModel.generateNumbers(it)
        }

        recyclerView.adapter = adapter

        viewModel.lotteries.observe(this){
            adapter.submitList(it)
        }

        addButton.setOnClickListener {
            viewModel.addLotteryAndGenerate()
            Toast.makeText(this, "Button Clicked!!!", Toast.LENGTH_SHORT).show()
        }

    }
}