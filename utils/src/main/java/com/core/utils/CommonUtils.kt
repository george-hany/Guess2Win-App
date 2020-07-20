package com.core.utils

import android.app.ProgressDialog
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.annotation.LayoutRes
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale.*

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

    fun updateAppLanguage(langCode: String, res: Resources) {
        val locale = Locale(langCode)
        val config = Configuration(res.configuration)
        setDefault(locale)
        config.setLocale(locale)

        res.updateConfiguration(
            config,
            res.displayMetrics
        )
    }

    fun getCurrentDate(): String {
        val DATE_PICKER_YYYY_MM_DD_FORMAT = SimpleDateFormat("yyyy-MM-dd", getDefault())
        val date = Calendar.getInstance().time
        return DATE_PICKER_YYYY_MM_DD_FORMAT.format(date)
    }

    fun getDateBeforeOrAfter(date: String, days: Int): String {
        val DATE_PICKER_YYYY_MM_DD_FORMAT = SimpleDateFormat("yyyy-MM-dd", getDefault())
        val myDate = DATE_PICKER_YYYY_MM_DD_FORMAT.parse(date)
        val calendar = Calendar.getInstance()
        calendar.time = myDate!!
        calendar.add(Calendar.DAY_OF_YEAR, days)
        return DATE_PICKER_YYYY_MM_DD_FORMAT.format(calendar.time)
    }
}
