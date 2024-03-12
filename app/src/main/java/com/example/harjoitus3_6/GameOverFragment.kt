package com.example.harjoitus3_6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.harjoitus3_6.databinding.FragmentAdditionBinding
import com.example.harjoitus3_6.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    private lateinit var binding: FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameOverBinding.inflate(inflater, container, false)

        val calculation = arguments?.getString("Calculation")
        val points = arguments?.getInt("Points")
        val time = arguments?.getInt("Time")
        val numQuestions = arguments?.getInt("NumQuestions")
        var text = ""

        if (calculation == "sum") {
            text = getString(R.string.thanks_sums)
        } else if (calculation == "deduction") {
            text = getString(R.string.thanks_deductions)
        }
        binding.textView10.text = text
        binding.textViewPointsOver.text = "${points.toString()} / $numQuestions"
        val mins = time?.div(60)
        val secs = time?.rem(60)

        if (secs != null) {
            if (secs < 10) {
                binding.textViewTimerOver.text = "$mins : 0$secs"
            } else {
                binding.textViewTimerOver.text = "$mins : $secs"
            }
        }

        binding.buttonAgain.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_startFragment)
        }

        return binding.root
    }
}