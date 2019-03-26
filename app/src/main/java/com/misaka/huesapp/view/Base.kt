package com.misaka.huesapp.view

interface Base {
    fun showOpenImageDialog()
    fun checkCameraPermission()
    fun dispatchTakePictureIntent()
    fun showGalleryIntent()
    fun checkWriteDataPermission(): Boolean
}