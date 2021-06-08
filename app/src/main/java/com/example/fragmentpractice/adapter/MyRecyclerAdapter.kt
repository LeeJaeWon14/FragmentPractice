package com.example.fragmentpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.fragmentpractice.MainActivity
import com.example.fragmentpractice.R
import com.example.fragmentpractice.anim.DepthPageTransformer

class MyRecyclerAdapter(private val count : Int) : RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerViewHolder>() {
    class MyRecyclerViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val viewPager = view.findViewById<ViewPager2>(R.id.vp_pager)
    }

    private var context : Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        context = parent.context
        return MyRecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        holder.viewPager.adapter = MyPagerAdapter(context as MainActivity)
        holder.viewPager.setPageTransformer(DepthPageTransformer())
    }
}