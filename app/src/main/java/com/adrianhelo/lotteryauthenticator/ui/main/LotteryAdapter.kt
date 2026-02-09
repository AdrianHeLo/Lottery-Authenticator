package com.adrianhelo.lotteryauthenticator.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adrianhelo.lotteryauthenticator.R
import com.adrianhelo.lotteryauthenticator.data.local.LotteryEntity
import com.adrianhelo.lotteryauthenticator.utils.CountdownTimer

class LotteryAdapter(private val onGenerate: (LotteryEntity) -> Unit): ListAdapter<LotteryEntity, LotteryAdapter.ViewHolder>(Diff()){

        inner class ViewHolder(v: View): RecyclerView.ViewHolder(v){

            val name: TextView
            val numbers: TextView
            val timer: TextView

            init {
                name = v.findViewById(R.id.name)
                numbers = v.findViewById(R.id.numbers)
                timer  = v.findViewById(R.id.timer)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lottery, parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            holder.name.text = item.name
            holder.numbers.text = item.currentsNumbers
            val countdown = CountdownTimer(
                item.intervalSeconds,
                {holder.timer.text = "${it}s"},
                {onGenerate(item)}
            )
            countdown.start()
        }

        class Diff : DiffUtil.ItemCallback<LotteryEntity>() {
                override fun areItemsTheSame(a: LotteryEntity, b: LotteryEntity) = a.id == b.id
                override fun areContentsTheSame(a: LotteryEntity, b: LotteryEntity) = a == b
            }
}
