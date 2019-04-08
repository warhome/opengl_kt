package com.misaka.huesapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.misaka.huesapp.R
import com.misaka.huesapp.contract.StyleContract
import com.misaka.huesapp.model.PresetHelper
import com.misaka.huesapp.model.StyleHelper
import com.misaka.huesapp.presenter.StylePresenter
import com.misaka.huesapp.view.activity.MainActivity
import com.misaka.huesapp.view.adapter.StyleAdapter
import kotlinx.android.synthetic.main.fragment_preset.view.*

class StyleFragment: Fragment(), StyleContract.View {

    private var presenter: StylePresenter? = null

    var layout: View? = null

    companion object {
        fun newInstance(): StyleFragment =
            StyleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layout = inflater.inflate(R.layout.fragment_style, container, false)

        presenter = StylePresenter(this)
        presenter?.initStyles()

        retainInstance

        return layout
    }

    override fun displayStyles() {
        layout?.recyclerView?.layoutManager = LinearLayoutManager(layout?.context, LinearLayout.HORIZONTAL, false)
        layout?.recyclerView?.adapter = StyleAdapter(StyleHelper.getStyleList()) { position -> (activity as MainActivity).setStyle(position)}
    }
}