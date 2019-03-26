package com.misaka.huesapp.contract

interface MainContract {
    interface View {
        fun init()
        fun showOpenImageDialog()
        fun setVisibility()
        fun animateFabMenu()
        fun setDefaultUIPosition()
        fun saveImageFromGLRenderer()
        fun startShareIntent()
    }

    interface Presenter {
        fun openImage()
        fun setViewsVisible()
        fun startFabMenuAnimation()
        fun resetUI()
        fun saveImage()
        fun shareImage()
    }
}