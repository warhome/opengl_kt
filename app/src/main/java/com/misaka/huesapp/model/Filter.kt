package com.misaka.huesapp.model

class Filter(name: String, param1: Float) {

    var name: String? = name
    var params: MutableList<Float> = ArrayList()

    init {
        params.add(param1)
        params.add(0f)
    }
}