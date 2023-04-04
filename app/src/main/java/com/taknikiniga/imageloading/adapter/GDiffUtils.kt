package com.taknikiniga.imageloading.adapter

import androidx.recyclerview.widget.DiffUtil

class GDiffUtils : DiffUtil.ItemCallback<GModel>() {
    override fun areItemsTheSame(oldItem: GModel, newItem: GModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GModel, newItem: GModel): Boolean {
       return oldItem == newItem
    }
}