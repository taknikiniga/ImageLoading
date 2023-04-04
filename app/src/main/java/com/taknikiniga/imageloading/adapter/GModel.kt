package com.taknikiniga.imageloading.adapter

sealed class GModel {
    data class ImageLoadingData(var url:String) : GModel()
}