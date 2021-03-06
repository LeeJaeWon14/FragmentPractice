package com.example.fragmentpractice.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentpractice.R
import com.example.fragmentpractice.databinding.FragmentLayoutBinding

class FixFragment : Fragment() {
    private var act : Context? = null
    private var _binding : FragmentLayoutBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance(page : Int) : FixFragment {
            val fragment = FixFragment()
            val args = Bundle()
            args.putInt("page", page)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Activity) act =  context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(requireArguments().get("page")) {
            0 -> { binding.thumbnail.setImageResource(R.drawable.photo1) }
            1 -> { binding.thumbnail.setImageResource(R.drawable.photo2) }
            2 -> { binding.thumbnail.setImageResource(R.drawable.photo3) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}