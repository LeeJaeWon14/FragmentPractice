package com.example.fragmentpractice.anim

import android.animation.ArgbEvaluator
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.fragmentpractice.adapter.MyPagerAdapter

class AnimPageTransformer(private val viewPager : ViewPager2) : ViewPager2.PageTransformer, ViewPager2.OnPageChangeCallback() {
    private val argb = ArgbEvaluator()
    private var color = -1
    init {
        //viewPager.addOn
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)

        //color = argb.evaluate(positionOffset, (viewPager.adapter as MyPagerAdapter).)
    }

    override fun transformPage(page: View, position: Float) {
        TODO("Not yet implemented")
    }


}