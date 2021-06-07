package com.example.fragmentpractice.adapter

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragmentpractice.CONSTANS
import com.example.fragmentpractice.MainActivity
import com.example.fragmentpractice.fragments.FirstFragment
import com.example.fragmentpractice.fragments.SecondFragment
import com.example.fragmentpractice.fragments.ThirdFragment
import com.example.fragmentpractice.fragments.VariableFragment

class MyPagerAdapter(fragmentActivity: FragmentActivity, private val count : Int = 3) : FragmentStateAdapter(fragmentActivity) {
    private val act = fragmentActivity as MainActivity
    override fun getItemCount(): Int {
        when(act.inputMode) {
            CONSTANS.INPUT_MODE_FIX -> {
                return count
            }
            CONSTANS.INPUT_MODE_VARIABLE -> {
                return act.uriList.size
            }
        }
        return 0
    }

    override fun createFragment(position: Int): Fragment {
        when(act.inputMode) {
            CONSTANS.INPUT_MODE_FIX -> {
                when(position) {
                    0 -> { return FirstFragment.newInstance(position) }
                    1 -> { return SecondFragment.newInstance(position) }
                    2 -> { return ThirdFragment.newInstance(position) }
                }
            }
            CONSTANS.INPUT_MODE_VARIABLE -> {
                return VariableFragment.newInstance(position)
            }
        }

        return Fragment()
    }
}