package com.taknikiniga.imageloading.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.taknikiniga.imageloading.R
import com.taknikiniga.imageloading.databinding.ImageLytBinding

class GAdapter : ListAdapter<GModel, GViewHolder>(GDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GViewHolder {
       return when (viewType) {
            R.layout.image_lyt -> GViewHolder.ImageLoadingVH(
                ImageLytBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> throw java.lang.IllegalArgumentException("Invalid View Type")
        }
    }

    override fun onBindViewHolder(holder: GViewHolder, position: Int) {
        when(holder){
            is GViewHolder.ImageLoadingVH -> holder.bind(getItem(position) as GModel.ImageLoadingData)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is GModel.ImageLoadingData -> R.layout.image_lyt
        }
    }
}