package com.sevenwinds.coffeeapp.presentation.coffee_house_menu_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sevenwinds.coffeeapp.R
import com.sevenwinds.coffeeapp.databinding.CardCoffeeHouseMenuBinding
import com.sevenwinds.domain.registration.coffee_house_menu.CoffeeHouseMenuItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuItemAdapter @Inject constructor() :
    ListAdapter<CoffeeHouseMenuItem, MenuItemAdapter.MenuItemViewHolder>(MenuItemDiffCallback) {
    inner class MenuItemViewHolder(private val binding: CardCoffeeHouseMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CoffeeHouseMenuItem) {
            with(binding) {
                name.text = item.name
                count.text = item.price.toString()
                count.text = String.format(binding.root.context.getString(R.string.rub_format), item.price)
                image.load(item.imageURL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val binding = CardCoffeeHouseMenuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        val MenuItemDiffCallback = object : DiffUtil.ItemCallback<CoffeeHouseMenuItem>() {
            override fun areItemsTheSame(
                oldItem: CoffeeHouseMenuItem,
                newItem: CoffeeHouseMenuItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CoffeeHouseMenuItem,
                newItem: CoffeeHouseMenuItem
            ): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }
}