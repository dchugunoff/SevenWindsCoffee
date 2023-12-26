package com.sevenwinds.coffeeapp.presentation.cofee_shops_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sevenwinds.coffeeapp.databinding.FragmentCoffeeShopsBinding

class CoffeeShopsFragment: Fragment() {

    private var _binding: FragmentCoffeeShopsBinding? = null
    private val binding: FragmentCoffeeShopsBinding
        get() = _binding ?: throw RuntimeException("FragmentCoffeeShopsBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeShopsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}