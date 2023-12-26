package com.sevenwinds.coffeeapp.presentation.cofee_shops_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sevenwinds.coffeeapp.databinding.FragmentCoffeeShopsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoffeeShopsFragment: Fragment() {

    private var _binding: FragmentCoffeeShopsBinding? = null
    private val binding: FragmentCoffeeShopsBinding
        get() = _binding ?: throw RuntimeException("FragmentCoffeeShopsBinding == null")

    private val viewModel: CoffeeShopsViewModel by viewModels()

    @Inject
    lateinit var adapter: CoffeeShopAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeShopsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAdapter()
    }

    private fun bindAdapter() {
        adapter.setNavController(findNavController())
        viewModel.coffeeShopsList.observe(viewLifecycleOwner) {
            binding.coffeeShopsRv.adapter = adapter
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}