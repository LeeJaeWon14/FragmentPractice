package com.example.fragmentpractice.anim

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class DepthPageTransformer : ViewPager2.PageTransformer {
    private val MIN_SCALE : Float = 0.75F
    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width

        if(position < -1) { // ~ -1
            page.alpha = 0f
        } else if(position <= 0) { // -1, 0
            with(page) {
                alpha = 1f
                translationX = 0f
                scaleX = 1f
                scaleY = 1f
            }
        } else if(position <= 1) { // 0, 1
            val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
            with(page) {
                alpha = 1 - position
                translationX = pageWidth * -position
                scaleX = scaleFactor
                scaleY = scaleFactor
            }
        } else {
            page.alpha = 0f
        }
    }
}