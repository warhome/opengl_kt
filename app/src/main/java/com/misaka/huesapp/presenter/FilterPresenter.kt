package com.misaka.huesapp.presenter

import com.misaka.huesapp.contract.FilterContract

class FilterPresenter(_view: FilterContract.View) {
    private var view: FilterContract.View = _view

    init {
        view.init()
    }
}