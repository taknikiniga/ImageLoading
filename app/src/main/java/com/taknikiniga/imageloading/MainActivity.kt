package com.taknikiniga.imageloading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.taknikiniga.imageloading.adapter.GAdapter
import com.taknikiniga.imageloading.adapter.GModel
import com.taknikiniga.imageloading.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    val viewmodel: MainViewModel by viewModels()
    val gAdapter = GAdapter()
    val listOrImgUrls = mutableListOf<GModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        lifecycleScope.launchWhenStarted {
         val urls = viewmodel.getUnsplash()[0].getAsJsonObject("urls")
            for (i in 0 until urls.size()){
                Log.e("Urlsss", "${urls}", )
                listOrImgUrls.add(GModel.ImageLoadingData(urls.get("small").asString))
            }

            initRecyclerView()

        }



    }

    private fun initRecyclerView() {
        gAdapter.submitList(listOrImgUrls)
        binding.recyclerview.adapter = gAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
    }
}