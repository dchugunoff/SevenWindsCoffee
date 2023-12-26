package com.sevenwinds.coffeeapp.presentation.cofee_shops_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sevenwinds.coffeeapp.databinding.CardCoffeeShopBinding
import com.sevenwinds.domain.registration.coffee_shops.CoffeeShopItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoffeeShopAdapter @Inject constructor() :
    ListAdapter<CoffeeShopItem, CoffeeShopAdapter.CoffeeShopViewHolder>(DiffCallback) {
    inner class CoffeeShopViewHolder(private val binding: CardCoffeeShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shop: CoffeeShopItem) {
            with(binding) {
                nameCoffeeShop.text = shop.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeShopViewHolder {
        val binding = CardCoffeeShopBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoffeeShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoffeeShopViewHolder, position: Int) {
        val shop = getItem(position)
        holder.bind(shop)
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<CoffeeShopItem>() {
            override fun areItemsTheSame(
                oldItem: CoffeeShopItem,
                newItem: CoffeeShopItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CoffeeShopItem,
                newItem: CoffeeShopItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }


}