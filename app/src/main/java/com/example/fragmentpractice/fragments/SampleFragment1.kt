package com.example.fragmentpractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentpractice.FragmentSampleActivity
import com.example.fragmentpractice.databinding.FragmentSample1Binding

class SampleFragment1 : Fragment() {
    private var _binding : FragmentSample1Binding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSample1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvFrag1.setOnClickListener {
            (activity as FragmentSampleActivity).changeFragment(1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(context, "Fragment1 destroy", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}