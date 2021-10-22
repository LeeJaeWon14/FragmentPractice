package com.example.fragmentpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fragmentpractice.databinding.ActivityFragmentSampleBinding
import com.example.fragmentpractice.fragments.SampleFragment1
import com.example.fragmentpractice.fragments.SampleFragment2

class FragmentSampleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFragmentSampleBinding
    private val fragMap : HashMap<Int, Fragment> = HashMap<Int, Fragment>().apply {
        put(0, SampleFragment1())
        put(1, SampleFragment2())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFrag1.setOnClickListener {
            for(fragment in supportFragmentManager.fragments) {
                if(fragment.isVisible && fragment is SampleFragment1) {
                    return@setOnClickListener
                }
            }
            changeFragment(0)
        }

        binding.btnFrag2.setOnClickListener {
            for(fragment in supportFragmentManager.fragments) {
                if(fragment.isVisible && fragment is SampleFragment2) {
                    return@setOnClickListener
                }
            }
            changeFragment(1)
        }
    }
    
    fun changeFragment(index : Int) {
        fragMap.get(index)?.let {
            supportFragmentManager.beginTransaction().replace(R.id.fl_fragment, it).commitAllowingStateLoss()
        }
    }
}