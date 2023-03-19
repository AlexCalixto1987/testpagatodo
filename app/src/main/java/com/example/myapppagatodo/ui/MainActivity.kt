package com.example.myapppagatodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.myapppagatodo.data.CustomArrayAdapter
import com.example.myapppagatodo.data.model.Bank
import com.example.myapppagatodo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var namesBanks: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewBankList()
        initView()
    }

    private fun initViewBankList() {
        binding.btnViewList.visibility = View.VISIBLE
        binding.lvBankList.visibility = View.VISIBLE
    }

    private fun onProcessBankList(banks: ArrayList<Bank>) {
        Log.e("Log","$banks")
        namesBanks = ArrayList<String>()
        namesBanks!!.clear()
        var j = banks.size
        while(0 < j ) {
            namesBanks!!.add(banks[j-1].bankName.toString())
            namesBanks!!.add(banks[j-1].description.toString())
            namesBanks!!.add(banks[j-1].age.toString()+" Años")
            namesBanks!!.add(banks[j-1].url.toString())
            j--
        }
        Log.e("namesBanks","$namesBanks")
    }

    private fun initView() {
        viewModel.getBanksLiveData().observe(this) { result ->
            result?.let { onProcessBankList(it) }
        }
        namesBanks = ArrayList<String>()
        namesBanks!!.clear()

        viewModel.getBankList()
        binding.btnViewList.setOnClickListener {
            val context = applicationContext
            val adapter = CustomArrayAdapter<String>(context, android.R.layout.simple_list_item_1, namesBanks!!)
            binding.lvBankList.setAdapter(adapter)
            binding.lvBankList.isClickable = true
            binding.lvBankList.setOnItemClickListener { adapterView, view, i, l ->
                fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if(p2==0) {
                        var dataBanks = "Description:" + "age" + "url"
                        AlertDialog.Builder(this)
                            .setTitle("Detalles").setMessage(dataBanks)
                            .create()
                            .show()
                    }
                }
            }
        }
    }
}