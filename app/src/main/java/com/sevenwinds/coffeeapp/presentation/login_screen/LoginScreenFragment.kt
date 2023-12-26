package com.sevenwinds.coffeeapp.presentation.login_screen

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
import com.sevenwinds.coffeeapp.databinding.FragmentLoginScreenBinding
import com.sevenwinds.coffeeapp.presentation.utils.ErrorResponse
import com.sevenwinds.coffeeapp.presentation.utils.SuccessResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginScreenFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val binding: FragmentLoginScreenBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginScreenBinding == null")

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkStateData()
        bindOnClickListeners()
    }

    private fun checkStateData() {
        viewModel.dataState.observe(viewLifecycleOwner) {
            when (it) {
                ErrorResponse -> {
                    Toast.makeText(
                        requireContext(),
                        "Ошибка при авторизации, попробуйте позже",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                SuccessResponse -> {
                    findNavController().navigate(R.id.action_loginScreenFragment_to_coffeeShopsFragment)
                }
            }
        }
    }

    private fun bindOnClickListeners() {
        binding.registrationButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginScreenFragment_to_registrationScreenFragment)
        }
        with(binding) {
            registrationButton.setOnClickListener {
                findNavController().navigate(R.id.action_loginScreenFragment_to_registrationScreenFragment)
            }
            loginButton.setOnClickListener {
                login()
            }
        }
    }

    private fun login() {
        val login = binding.emailEdittext.text
        val password = binding.passwordEdittext.text
        if (checkInputFields()) {
            viewModel.loginUser(login.toString(), password.toString())
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
        return isValid
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}