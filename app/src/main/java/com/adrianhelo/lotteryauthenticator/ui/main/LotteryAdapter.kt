package com.adrianhelo.lotteryauthenticator.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adrianhelo.lotteryauthenticator.R
import com.adrianhelo.lotteryauthenticator.data.local.LotteryEntity
import com.adrianhelo.lotteryauthenticator.databinding.ItemLotteryBinding

class LotteryAdapter(private val itemList: List<LotteryEntity>,
                     private val clickListener: (LotteryEntity)-> Unit): RecyclerView.Adapter<LotteryAdapter.LotteryViewHolder>(){

        inner class LotteryViewHolder(private val itemLotteryBinding: ItemLotteryBinding): RecyclerView.ViewHolder(itemLotteryBinding.root){
            fun bind(item: LotteryEntity, clickListener: (LotteryEntity) -> Unit){
                itemLotteryBinding.name.text = item.name
                itemLotteryBinding.numbers.text = item.currentsNumbers
                itemLotteryBinding.timer.text = item.intervalSeconds.toString()
                itemLotteryBinding.lotteryInfoText.setOnClickListener {
                    clickListener(item)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotteryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val bindingInflater: ItemLotteryBinding = DataBindingUtil.inflate(inflater, R.layout.item_lottery, parent, false)
            return LotteryViewHolder(bindingInflater)
        }

        override fun getItemCount(): Int {
            return itemList.size
        }

        override fun onBindViewHolder(holder: LotteryViewHolder, position: Int) {
            holder.bind(itemList[position], clickListener)
        }
}
