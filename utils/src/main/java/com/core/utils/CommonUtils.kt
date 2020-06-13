package com.core.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.annotation.LayoutRes
import java.io.InputStream

object CommonUtils {

    fun showLoadingDialog(context: Context, @LayoutRes resId: Int): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.apply {
            setContentView(resId)
            isIndeterminate = true
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
        return progressDialog
    }

    fun loadJSONFromAsset(
        context: Context,
        jsonFileName: String
    ): String {
        val manager = context.assets
        val `is`: InputStream = manager.open(jsonFileName)
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        return String(buffer)
    }
}
