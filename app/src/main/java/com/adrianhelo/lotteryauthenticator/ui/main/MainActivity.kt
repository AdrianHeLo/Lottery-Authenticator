package com.adrianhelo.lotteryauthenticator.ui.main

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrianhelo.lotteryauthenticator.R
import com.adrianhelo.lotteryauthenticator.data.local.AppDatabase
import com.adrianhelo.lotteryauthenticator.data.local.LotteryEntity
import com.adrianhelo.lotteryauthenticator.databinding.ActivityMainBinding
import com.adrianhelo.lotteryauthenticator.domain.LotteryFactory
import com.adrianhelo.lotteryauthenticator.domain.generator.LotteryGenerator
import com.adrianhelo.lotteryauthenticator.domain.repository.LotteryRepository

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var lotteryViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val addButton = findViewById<Button>(R.id.addLotteryBtn)

        // Room
        val dao = AppDatabase.getInstance(application.applicationContext).getLotteryDao()
        val repository = LotteryRepository(dao)

        // View Model
        val factory = LotteryFactory(repository)
        lotteryViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        activityMainBinding.lotteryVM = lotteryViewModel
        activityMainBinding.lifecycleOwner = this

        initRecyclerView()
        addButton.setOnClickListener {
            lotteryViewModel.addLotteryAndGenerate()
            Toast.makeText(this, "Button Clicked!!!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initRecyclerView() {
        activityMainBinding.containerRecyclerView.layoutManager = LinearLayoutManager(this)
        displayData()
    }

    private fun displayData() {
        lotteryViewModel.lotteries.observe(this){
            activityMainBinding.containerRecyclerView.adapter = LotteryAdapter(it) { item: LotteryEntity ->
                listItemClicked(
                    item
                )
            }
        }
    }

    private fun listItemClicked(selectItem: LotteryEntity) {
        Toast.makeText(this, "Selected name is ${selectItem.name}", Toast.LENGTH_LONG).show()
    }
}