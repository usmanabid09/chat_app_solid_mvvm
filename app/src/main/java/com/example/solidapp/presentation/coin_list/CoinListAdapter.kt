package com.example.solidapp.presentation.coin_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.solidapp.R
import com.example.solidapp.domain.model.Coin

class CoinListAdapter(
    private val coins: List<Coin>
) : RecyclerView.Adapter<CoinViewHolder>() {

    var coinListItemClick: ((Coin) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.coin_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coin = coins[position], coinListItemClick)
    }

    override fun getItemCount(): Int = coins.size
}

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewCoinName: TextView = itemView.findViewById(R.id.textViewCoinName)

    fun bind(coin: Coin, onClick: ((Coin) -> Unit)?) {
        textViewCoinName.text = coin.name
        textViewCoinName.setOnClickListener { onClick?.invoke(coin) }
    }
}