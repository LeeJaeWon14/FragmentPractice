package com.example.fragmentpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentpractice.adapter.MyRecyclerAdapter
import com.example.fragmentpractice.databinding.ActivityMainBinding
import com.example.fragmentpractice.databinding.InitDialogLayoutBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showInitDialog()
    }

    private fun showInitDialog() {
        val dlgView = InitDialogLayoutBinding.inflate(layoutInflater)
        val dlg = AlertDialog.Builder(this@MainActivity).create()
        dlg.setView(dlgView.root)
        dlg.window!!.setBackgroundDrawableResource(R.drawable.block)

        dlgView.btnInput.setOnClickListener {
            Log.i("dialog", "input")
            binding.rvFragmentList.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvFragmentList.adapter = MyRecyclerAdapter(
                dlgView.edtInputCount.text.toString().toInt()
            )
            dlg.dismiss()
        }

        dlgView.btnExit.setOnClickListener {
            Log.i("dialog", "exit")
            //finishAffinity()
            exitProcess(0)
        }

        dlg.setCanceledOnTouchOutside(false)
        dlg.show()
    }
}