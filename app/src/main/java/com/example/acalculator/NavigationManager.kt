package com.example.acalculator

import android.R.attr.key
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class NavigationManager {
    companion object{
        private fun placeFragment(fm: FragmentManager, fragment: Fragment){
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }
        fun goToCalculatorFragment(fm: FragmentManager, operations: MutableList<Operation>?){
            val calcFragment = CalculatorFragment.newInstance(operations)
            placeFragment(fm, calcFragment)
        }
        fun goToHistoryFragment(fm: FragmentManager, operations: MutableList<Operation>?){
            val histFragment = HistoryFragment.newInstance(operations)
            placeFragment(fm, histFragment)
        }
    }
}