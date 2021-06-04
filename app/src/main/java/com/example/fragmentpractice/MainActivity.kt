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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showInitDialog()


    }

    private val requestActivity : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if(it.resultCode == Activity.RESULT_OK) {
            it.data!!.let {
                try {
                    val list = ArrayList<Uri>()

                    //사진이 하나인 경우, clipData가 아닌 data로 uri만 보냄
                    //사진이 여러 장인 경우, clipdata로 들어옴
                    if(it.data != null)
                        list.add(it.data!!)
                    if(it.clipData != null) {
                        val clip = it.clipData!!

                        for(index in 0.. clip.itemCount) {
                            list.add(clip.getItemAt(index).uri)
                        }
                    }


                } catch (e : Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun showInitDialog() {
        val dlgView = InitDialogLayoutBinding.inflate(layoutInflater)
        val dlg = AlertDialog.Builder(this@MainActivity).create()
        dlg.setView(dlgView.root)
        dlg.window!!.setBackgroundDrawableResource(R.drawable.block)

        var inputMode : String? = null

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
                    Toast.makeText(this@MainActivity, "구현중", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
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
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) //여러장 선택
        requestActivity.launch(intent)
    }
}