package com.example.fragmentpractice.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragmentpractice.fragments.FirstFragment
import com.example.fragmentpractice.fragments.SecondFragment
import com.example.fragmentpractice.fragments.ThirdFragment

class MyPagerAdapter(fragmentActivity: FragmentActivity, private val count : Int = 3) : FragmentStateAdapter(fragmentActivity) {
    //var fragments = ArrayList<Fragment>()
    override fun getItemCount(): Int {
        return count
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> { return FirstFragment.newInstance(position) }
            1 -> { return SecondFragment.newInstance(position) }
            2 -> { return ThirdFragment.newInstance(position) }
        }

        return Fragment()
    }
}