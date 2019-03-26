package com.misaka.huesapp.contract

interface PresetContract {
    interface View{
        fun displayPresets()
    }
    interface Presenter{
        fun initPresets()
    }
}