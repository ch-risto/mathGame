package com.example.harjoitus3_6

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected var time = 0
    protected var num1 = 0
    protected var num2 = 0
    protected var questionIndex = 0
    protected var points = 0

    protected open fun saveInstanceState(outState: Bundle) {
        outState.putInt("SecondsCount", time)
        outState.putInt("Num1", num1)
        outState.putInt("Num2", num2)
        outState.putInt("QuestionIndex", questionIndex)
        outState.putInt("Points", points)
    }

    protected open fun restoreInstanceState(savedInstanceState: Bundle?) {
        time = savedInstanceState?.getInt("SecondsCount") ?: 0
        num1 = savedInstanceState?.getInt("Num1") ?: 0
        num2 = savedInstanceState?.getInt("Num2") ?: 0
        questionIndex = savedInstanceState?.getInt("QuestionIndex") ?: 0
        points = savedInstanceState?.getInt("Points") ?: 0

        if (num1 == 0 || num2 == 0) {
            num1 = (1..10).random()
            num2 = (1..10).random()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        restoreInstanceState(savedInstanceState)
    }
}