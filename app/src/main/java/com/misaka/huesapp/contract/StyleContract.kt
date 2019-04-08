package com.misaka.huesapp.contract

interface StyleContract {
    interface View{
        fun displayStyles()
    }
    interface Presenter{
        fun initStyles()
    }
}