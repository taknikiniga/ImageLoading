package com.taknikiniga.imageloading.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.squareup.picasso.Picasso
import com.taknikiniga.imageloading.databinding.ImageLytBinding
import com.taknikiniga.imageloading.di.MainApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest

sealed class GViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    class ImageLoadingVH(val binding: ImageLytBinding) : GViewHolder(binding.root) {
        fun bind(item: GModel.ImageLoadingData) {

            binding.textView.text = item.url

//            Log.e("ItemUrls", item.url )
//
//            CoroutineScope(Dispatchers.Main).launch {
//                Picasso.get().load(item.url).into(binding.imageLoading)
//            }


//            Glide.with(binding.root.context)
//                .load(item.url.toUri().buildUpon().scheme("https").build())
//                .into(binding.imageLoading)

//            Glide.with(binding.root).load(item.url).override(80,400).into(binding.imageLoading)

            CoroutineScope(Dispatchers.Main).launch {
                val MAX_BITMAP_SIZE = 1080 * 2102
                val bitmapPool = LruBitmapPool(MAX_BITMAP_SIZE.toLong())
                 withContext(Dispatchers.Main) {

                     val bitmap = loadUrls(item.url,bitmapPool,binding.root.context)
                     binding.imageLoading.setImageBitmap(bitmap)
                 }

            }
        }

        private suspend fun loadUrls(
            urls: String,
            bitmapPool: BitmapPool,
            context: Context
        ): Bitmap? = withContext(Dispatchers.IO) {
            val requestBuilder =
                RequestOptions().signature(ObjectKey(urls)).format(DecodeFormat.PREFER_ARGB_8888)
                    .disallowHardwareConfig().transform(BitmapPoolAdapter(bitmapPool))

            Glide.with(context.applicationContext).asBitmap().apply(requestBuilder).load(urls)
                .submit().get()

        }
    }


}

private class BitmapPoolAdapter(private val bitmapPool: BitmapPool) : BitmapTransformation() {
    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        if (pool == bitmapPool) {
            return toTransform
        }
        // Obtain a Bitmap from the pool
        val recycled = bitmapPool.get(toTransform.width, toTransform.height, toTransform.config)
        // Draw the original Bitmap onto the recycled Bitmap using a Canvas
        val canvas = Canvas(recycled)
        canvas.drawBitmap(toTransform, 0f, 0f, null)
        // Return the recycled Bitmap
        return recycled
    }

    override fun hashCode(): Int {
        return bitmapPool.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(bitmapPool.toString().toByteArray(CHARSET))
    }
}







