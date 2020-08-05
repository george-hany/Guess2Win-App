package com.core.base

import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.core.base.databinding.ProgressDialogViewBinding

class CustomProgressDialog {

    var dialog: CustomDialog? = null

    fun show(context: Context): Dialog {
        return show(context, null)
    }

    fun show(context: Context, title: CharSequence?): Dialog {
        val view: ProgressDialogViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.progress_dialog_view,
            null,
            false
        )

        if (title != null) {
            view.cpTitle.text = title
        }

        // Card Color
        view.cpCardview.setCardBackgroundColor(
            ResourcesCompat.getColor(
                context.resources,
                R.color.dialogCardViewBackground,
                null
            )
        )

        // Progress Bar Color
        setColorFilter(
            view.cpPbar.indeterminateDrawable,
            ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null)
        )

        // Text Color
        view.cpTitle.setTextColor(Color.WHITE)

        dialog = CustomDialog(context)
        dialog?.setContentView(view.root)
        dialog?.show()
        return dialog!!
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            // Set Semi-Transparent Color for Dialog Background
            window?.decorView?.rootView?.setBackgroundResource(R.color.dialogBackground)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}