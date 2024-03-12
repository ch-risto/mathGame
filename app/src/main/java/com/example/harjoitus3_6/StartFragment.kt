package com.example.harjoitus3_6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.harjoitus3_6.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.buttonAddition.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_additionFragment)
        }

        binding.buttonDeduction.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_deductionFragment)
        }

        return binding.root
    }
}