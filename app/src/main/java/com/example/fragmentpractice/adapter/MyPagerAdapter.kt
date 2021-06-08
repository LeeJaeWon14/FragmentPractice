package com.example.fragmentpractice.adapter

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragmentpractice.CONSTANS
import com.example.fragmentpractice.MainActivity
import com.example.fragmentpractice.fragments.*

class MyPagerAdapter(fragmentActivity: FragmentActivity, private val count : Int = 3) : FragmentStateAdapter(fragmentActivity) {
    private val act = fragmentActivity as MainActivity
    override fun getItemCount(): Int {
        return when(act.inputMode) {
            CONSTANS.INPUT_MODE_VARIABLE -> {
                act.uriList.size
            }
            else -> {
                count
            }
        }
    }

    override fun createFragment(position: Int): Fragment {
        return when(act.inputMode) {
            CONSTANS.INPUT_MODE_FIX -> {
                FixFragment.newInstance(position)
            }
            CONSTANS.INPUT_MODE_VARIABLE -> {
                VariableFragment.newInstance(position)
            }
            else -> {
                Fragment()
            }
        }
    }
}