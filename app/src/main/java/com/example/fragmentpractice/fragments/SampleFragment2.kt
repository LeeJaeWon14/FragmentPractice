package com.example.fragmentpractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentpractice.FragmentSampleActivity
import com.example.fragmentpractice.R
import com.example.fragmentpractice.databinding.FragmentSample1Binding
import com.example.fragmentpractice.databinding.FragmentSample2Binding

class SampleFragment2 : Fragment() {
    private var _binding : FragmentSample2Binding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSample2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvFrag2.setOnClickListener {
            (activity as FragmentSampleActivity).changeFragment(0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(context, "Fragment2 destroy", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}