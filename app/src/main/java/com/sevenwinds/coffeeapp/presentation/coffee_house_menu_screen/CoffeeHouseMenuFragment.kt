package com.sevenwinds.coffeeapp.presentation.coffee_house_menu_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sevenwinds.coffeeapp.databinding.FragmentCoffeeHouseMenuBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoffeeHouseMenuFragment : Fragment() {

    private var _binding: FragmentCoffeeHouseMenuBinding? = null
    private val binding: FragmentCoffeeHouseMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentCoffeeHouseMenuBinding == null")

    private val viewModel: CoffeeHouseMenuViewModel by viewModels()

    @Inject
    lateinit var adapter: MenuItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeHouseMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundleID = arguments?.getString("shop_id")
        Log.d("SHOP_ID", "$bundleID")
        viewModel.getMenu(bundleID ?: "1")
        bindView()
    }

    private fun bindView() {
        viewModel.items.observe(viewLifecycleOwner) {
            binding.menuItemRv.adapter = adapter
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}