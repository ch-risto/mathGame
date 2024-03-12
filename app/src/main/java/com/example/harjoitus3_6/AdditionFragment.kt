package com.example.harjoitus3_6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.harjoitus3_6.databinding.FragmentAdditionBinding

class AdditionFragment : BaseFragment(), TimerCallback {

    private lateinit var binding: FragmentAdditionBinding
    private lateinit var myTimer: MyTimer
    private val calculation = "sum"
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

        binding = FragmentAdditionBinding.inflate(inflater, container, false)

        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState)
        } else {
            mathExcercise()
        }

        binding.buttonCheckSum.setOnClickListener {

            checkAnswer()
            binding.editTextNumber2.text.clear()
            questionIndex++

            if (questionIndex < numQuestions) {
                binding.textViewExcerciseNumberA.text = "${questionIndex + 1} / $numQuestions"
                binding.textViewPointsA.text = "$points"

                mathExcercise()
            } else {
                val action = AdditionFragmentDirections.actionAdditionFragmentToGameOverFragment(
                    points,
                    calculation,
                    time,
                    numQuestions
                )
                findNavController().navigate(action)
            }
        }

        // Set texts for the beginning
        binding.textViewExcerciseNumberA.text = "${questionIndex + 1} / $numQuestions"
        binding.textViewPointsA.text = "$points"
        binding.textViewCalculationSum.text = "$num1 + $num2 ="
        // Timer
        myTimer = MyTimer(this)
        myTimer.secondsCount = time

        return binding.root
    }

    private fun mathExcercise() {
        num1 = (1..10).random()
        num2 = (1..10).random()
        binding.textViewCalculationSum.text = "$num1 + $num2 ="
    }

    private fun checkAnswer() {
        if (!binding.editTextNumber2.text.isNullOrBlank()) {
            userAnswer = binding.editTextNumber2.text.toString().toInt()
        }

        val answer = num1 + num2

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
        binding.textViewTimerA.text = formattedTime
        time = seconds
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState)
    }
}