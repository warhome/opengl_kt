package com.misaka.huesapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.misaka.huesapp.R

class EtcFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_etc, container, false)
    }

    companion object {
        fun newInstance(): EtcFragment =
            EtcFragment()
    }
}