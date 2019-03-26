package com.misaka.huesapp.model

import android.graphics.Bitmap
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class Renderer {
    companion object {
        private var workedImage: Bitmap? = null

        fun saveImage(bitmap: Bitmap ,dir: String, compress: Int): Uri{
            val file = File(dir,"${UUID.randomUUID()}.jpg")
            try{
                // Compress the bitmap and save in jpg format
                val stream: OutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG,compress,stream)
                stream.flush()
                stream.close()
            }catch (e: IOException){
                e.printStackTrace()
            }
            // Return the saved bitmap uri
            return Uri.fromFile(file)
        }

        fun setImage(bitmap: Bitmap) {
            workedImage = bitmap
        }

        fun getImage(): Bitmap? {
            return workedImage
        }
    }
}