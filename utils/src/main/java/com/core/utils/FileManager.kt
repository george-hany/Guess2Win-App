package com.core.utils

import android.content.Context
import android.util.Log
import java.io.*

class FileManager(var context: Context) {
    fun writeFile(text: String, fileName: String) {
        val bufferedWriter =
            BufferedWriter(FileWriter(File(context.filesDir.toString() + File.separator.toString() + fileName)))
        bufferedWriter.write(text)
        bufferedWriter.close()
    }

    fun readFile(strFileName: String): String? {
        var line: String? = null
        try {
            val fileInputStream =
                FileInputStream(File(context.filesDir.toString() + File.separator.toString() + strFileName)) // set file path & name to read
            val inputStreamReader =
                InputStreamReader(fileInputStream) // create input steam reader
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            while (bufferedReader.readLine().also { line = it } != null) { // read line by line
                stringBuilder.append(line + System.getProperty("line.separator")) // append the readed text line by line
            }
            fileInputStream.close()
            line = stringBuilder.toString() // finially the whole date into an single string
            bufferedReader.close()
            Log.e("DataStoreSD 3.1 ", line)
        } catch (ex: FileNotFoundException) {
            Log.e("DataStoreSD 3.2 ", ex.message)
        } catch (ex: IOException) {
            Log.e("DataStoreSD 3.3 ", ex.message)
        }
        return line
    }
}
