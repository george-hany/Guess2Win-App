package com.core.utils

import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object BindingUtils {
    @BindingAdapter("app:error")
    @JvmStatic
    fun bindError(view: EditText, error: String?) {
        if (error != null) {
            Log.e("Error", error)
            view.error = error
            view.requestFocus()
        }
    }

    @BindingAdapter("app:imageDrawableName")
    @JvmStatic
    fun bindimageDrawableName(view: ImageView, image: String?) {
        if (image != null) {
            val identifier =
                view.context.resources.getIdentifier(image, "drawable", view.context.packageName)
            view.setImageResource(identifier)
        }
    }
    @BindingAdapter("app:image")
    @JvmStatic
    fun bindImage(view: ImageView, url: String?) {
        if (url != null) {
            Glide.with(view.context).load(url).into(view)
        }
    }

    @BindingAdapter("app:adapter")
    @JvmStatic
    fun bindAdapter(view: RecyclerView, adapter: Any?) {
        if (adapter != null) {
            view.adapter = adapter as RecyclerView.Adapter<*>?
        }
    }
}
