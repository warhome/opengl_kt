package com.misaka.huesapp.presenter

import com.misaka.huesapp.contract.MainContract
import com.misaka.huesapp.contract.MainContract.Presenter
import com.misaka.huesapp.contract.MainContract.View

class MainPresenter(_View: MainContract.View): Presenter {

    private var view: View = _View

    init {
        view.init()
    }

    override fun openImage() {
        view.showOpenImageDialog()
    }

    override fun setViewsVisible() {
        view.setVisibility()
    }

    override fun startFabMenuAnimation() {
        view.animateFabMenu()
    }

    override fun resetUI() {
        view.setDefaultUIPosition()
    }

    override fun saveImage() {
        view.saveImageFromGLRenderer()
    }

    override fun shareImage() {
        view.startShareIntent()
    }

}