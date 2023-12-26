package com.sevenwinds.coffeeapp.presentation.registration_screen

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sevenwinds.coffeeapp.R
import com.sevenwinds.coffeeapp.databinding.FragmentRegistrationScreenBinding
import com.sevenwinds.coffeeapp.presentation.utils.ErrorResponse
import com.sevenwinds.coffeeapp.presentation.utils.SuccessResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationScreenFragment : Fragment() {

    private var _binding: FragmentRegistrationScreenBinding? = null
    private val binding: FragmentRegistrationScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentRegistrationScreenBinding == null")

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkStateData()
        binding.registrationButton.setOnClickListener {
            registration()
        }
    }

    private fun checkStateData() {
        viewModel.dataState.observe(viewLifecycleOwner) {
            when(it){
                ErrorResponse -> {
                    Toast.makeText(requireContext(), "Ошибка при регистрации, попробуйте позже", Toast.LENGTH_SHORT).show()
                }
                SuccessResponse -> {
                    findNavController().navigate(R.id.action_registrationScreenFragment_to_coffeeShopsFragment)
                }
            }
        }
    }

    private fun registration() {
        val login = binding.emailEdittext.text
        val password = binding.passwordEdittext.text
        if (checkInputFields()) {
            viewModel.registrationUser(login.toString(), password.toString())
        }
    }

    private fun checkInputFields(): Boolean {
        var isValid = true
        with(binding.emailEdittext) {
            if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                isValid = false
                error = context.getString(R.string.error_email)
            }
        }
        with(binding.passwordEdittext) {
            if (text.isNullOrBlank() || text.contains(' ')) {
                error = context.getString(R.string.error_first_pass)
                isValid = false
            }
        }
        with(binding.repeatPasswordEdittext) {
            val password = binding.passwordEdittext.text
            if (text != password) {
                error = context.getString(R.string.error_second_password)
                isValid = false
            }
        }
        return isValid
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}