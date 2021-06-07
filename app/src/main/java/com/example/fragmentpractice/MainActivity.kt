package com.example.fragmentpractice

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentpractice.adapter.MyRecyclerAdapter
import com.example.fragmentpractice.databinding.ActivityMainBinding
import com.example.fragmentpractice.databinding.InitDialogLayoutBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    val uriList = ArrayList<Uri>()
    var inputMode : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showInitDialog()
    }

    private val requestActivity = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) {
        uriList.addAll(it)

        binding.rvFragmentList.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvFragmentList.adapter = MyRecyclerAdapter(1)

        /*if(it.resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "RESULT_OK", Toast.LENGTH_SHORT).show()
            it.data!!.let {
                try {
                    val list = ArrayList<Uri>()

                    if(it == null) {
                        Toast.makeText(this, "is null", Toast.LENGTH_SHORT).show()
                    }

                    //사진이 하나인 경우, clipData가 아닌 data로 uri만 보냄
                    //사진이 여러 장인 경우, clipdata로 들어옴
                    it.data.let {
                        list.add(it!!)
                    }

                    it.clipData.let {
                        val clip = it!!

                        for(index in 0.. clip.itemCount) {
                            list.add(clip.getItemAt(index).uri)
                        }
                    }
                    uriList.addAll(list)

                    Toast.makeText(this, "ResultActivity", Toast.LENGTH_SHORT).show()
                    binding.rvFragmentList.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvFragmentList.adapter = MyRecyclerAdapter(1)
                } catch (e : Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            Toast.makeText(this, "Result 실패", Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun showInitDialog() {
        val dlgView = InitDialogLayoutBinding.inflate(layoutInflater)
        val dlg = AlertDialog.Builder(this@MainActivity).create()
        dlg.setView(dlgView.root)
        dlg.window!!.setBackgroundDrawableResource(R.drawable.block)

        dlgView.rdoGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rdoFix -> { inputMode = getString(R.string.str_radio_fix) }
                R.id.rdoVariable -> { inputMode = getString(R.string.str_radio_variable) }
            }
        }

        dlgView.btnInput.setOnClickListener {
            when(inputMode) {
                getString(R.string.str_radio_fix) -> {
                    binding.rvFragmentList.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvFragmentList.adapter = MyRecyclerAdapter(
                        dlgView.edtInputCount.text.toString().toInt()
                    )
                }
                getString(R.string.str_radio_variable) -> {
                    variableInit()
                }
            }
            dlg.dismiss()
        }

        dlgView.btnExit.setOnClickListener {
            finishAffinity()
        }

        dlg.setCanceledOnTouchOutside(false)
        dlg.show()
    }

    private fun variableInit() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) //여러장 선택
        requestActivity.launch("image/*")
    }
}