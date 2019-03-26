package com.misaka.huesapp.presenter

import com.misaka.huesapp.contract.PresetContract

class PresetPresenter(_view: PresetContract.View): PresetContract.Presenter {

    private var view: PresetContract.View = _view

    override fun initPresets() {
        view.displayPresets()
    }
}