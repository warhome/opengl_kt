package com.misaka.huesapp.presenter

import com.misaka.huesapp.contract.StyleContract

class StylePresenter(_view: StyleContract.View): StyleContract.Presenter{

    private var view: StyleContract.View = _view

    override fun initStyles() {
        view.displayStyles()
    }

}