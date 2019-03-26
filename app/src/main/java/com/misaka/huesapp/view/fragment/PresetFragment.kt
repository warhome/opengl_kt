package com.misaka.huesapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.misaka.huesapp.R
import com.misaka.huesapp.contract.PresetContract
import com.misaka.huesapp.model.PresetHelper
import com.misaka.huesapp.presenter.PresetPresenter
import com.misaka.huesapp.view.activity.MainActivity
import com.misaka.huesapp.view.adapter.PresetAdapter
import kotlinx.android.synthetic.main.fragment_preset.view.*

class PresetFragment: Fragment(), PresetContract.View {

    private var presenter: PresetPresenter? = null

    var layout: View? = null

    companion object {
        fun newInstance(): PresetFragment =
            PresetFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layout = inflater.inflate(R.layout.fragment_preset, container, false)

        presenter = PresetPresenter(this)
        presenter?.initPresets()

        retainInstance

        return layout
    }

    override fun displayPresets() {
        layout?.recyclerView?.layoutManager = LinearLayoutManager(layout?.context, LinearLayout.HORIZONTAL, false)
        layout?.recyclerView?.adapter = PresetAdapter(PresetHelper.getPresetList()) {position -> (activity as MainActivity).setPreset(position)}
    }
}