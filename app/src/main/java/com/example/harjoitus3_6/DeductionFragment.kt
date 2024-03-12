package com.example.harjoitus3_6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.harjoitus3_6.databinding.FragmentDeductionBinding

class DeductionFragment : BaseFragment(), TimerCallback {

    private lateinit var binding: FragmentDeductionBinding
    private lateinit var myTimer: MyTimer
    private val calculation = "deduction"
    private val numQuestions = 10
    private var userAnswer = 0

    // Hides the menu
    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDeductionBinding.inflate(inflater, container, false)

        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState)
        } else {
            mathExcercise()
        }

        binding.buttonCheckDeduction.setOnClickListener {

            checkAnswer()
            binding.editTextNumber.text.clear()
            questionIndex++

            if (questionIndex < numQuestions) {
                binding.textViewExcerciseNumber.text = "${questionIndex + 1} / $numQuestions"
                binding.textViewPoints.text = "$points"

                mathExcercise()
            } else {
                val action = DeductionFragmentDirections.actionDeductionFragmentToGameOverFragment(
                    points,
                    calculation,
                    time,
                    numQuestions
                )
                findNavController().navigate(action)
            }
        }

        // Set texts for the beginning
        binding.textViewExcerciseNumber.text = "${questionIndex + 1} / $numQuestions"
        binding.textViewPoints.text = "$points"
        binding.textViewCalculation.text = "$num1 - $num2 ="
        // Timer
        myTimer = MyTimer(this)
        myTimer.secondsCount = time

        return binding.root
    }

    private fun mathExcercise() {
        num1 = (1..10).random()
        num2 = (1..10).random()
        binding.textViewCalculation.text = "$num1 - $num2 ="
    }

    private fun checkAnswer() {
        if (!binding.editTextNumber.text.isNullOrBlank()) {
            userAnswer = binding.editTextNumber.text.toString().toInt()
        }

        val answer = num1 - num2

        if (answer == userAnswer) {
            points++
        }
    }

    // Timer

    override fun onResume() {
        super.onResume()
        myTimer.startTimer()
    }

    override fun onPause() {
        super.onPause()
        myTimer.stopTimer()
    }

    override fun updateTimer(seconds: Int, formattedTime: String) {
        binding.textViewTimer.text = formattedTime
        time = seconds
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState)
    }
}