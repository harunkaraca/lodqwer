package com.hk.loodosassigment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hk.loodosassigment.R
import com.hk.loodosassigment.data.model.Coin
import com.hk.loodosassigment.util.getProgressDrawable
import com.hk.loodosassigment.util.loadImage

class CoinListAdapter(var coins:MutableList<Coin>):RecyclerView.Adapter<CoinListAdapter.CoinViewHolder>() {

    fun updateCoins(newCoins:List<Coin>){
        coins.clear()
        coins.addAll(newCoins)
        notifyDataSetChanged()
    }

    class CoinViewHolder(view: View):RecyclerView.ViewHolder(view){

        private val nameText=view.findViewById<TextView>(R.id.name)
        private val priceText=view.findViewById<TextView>(R.id.price)
        private val imageView=view.findViewById<ImageView>(R.id.imageView)
        private val progressDrawable=getProgressDrawable(view.context)
        fun bind(coin: Coin){
            priceText.text=coin.price
            nameText.text=coin.name
            imageView.loadImage(coin.imageUrl,progressDrawable)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_coin,parent,false))
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position])
    }

    override fun getItemCount()=coins.size

}