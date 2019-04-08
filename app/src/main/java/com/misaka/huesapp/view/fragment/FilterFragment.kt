package com.misaka.huesapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SeekBar
import com.misaka.huesapp.R
import com.misaka.huesapp.contract.FilterContract
import com.misaka.huesapp.model.Filter
import com.misaka.huesapp.model.FilterHelper
import com.misaka.huesapp.presenter.FilterPresenter
import com.misaka.huesapp.view.activity.MainActivity
import kotlinx.android.synthetic.main.bottom_sheet.view.*

class FilterFragment: Fragment(), FilterContract.View, SeekBar.OnSeekBarChangeListener,
    CompoundButton.OnCheckedChangeListener {

    var layout: View? = null
    var filters: ArrayList<Filter> = ArrayList()
    var presenter: FilterPresenter? = null

    companion object {
        fun newInstance(): FilterFragment =
            FilterFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layout = inflater.inflate(R.layout.fragment_filter, container, false)
        presenter = FilterPresenter(this)

        retainInstance

        return layout
    }

    override fun init() {
        layout?.seekBarBright?.setOnSeekBarChangeListener(this)
        layout?.seekBarContrast?.setOnSeekBarChangeListener(this)
        layout?.seekBarFilllight?.setOnSeekBarChangeListener(this)
        layout?.seekBarFishEye?.setOnSeekBarChangeListener(this)
        layout?.seekBarTemperature?.setOnSeekBarChangeListener(this)
        layout?.seekBarBlack?.setOnSeekBarChangeListener(this)
        layout?.seekBarWhite?.setOnSeekBarChangeListener(this)

        layout?.autofixSwitch?.setOnCheckedChangeListener(this)
        layout?.crossprocessSwitch?.setOnCheckedChangeListener(this)
        layout?.documentarySwitch?.setOnCheckedChangeListener(this)
        layout?.negativeSwitch?.setOnCheckedChangeListener(this)
        layout?.BlackWhiteSwitch?.setOnCheckedChangeListener(this)
        layout?.posterizeSwitch?.setOnCheckedChangeListener(this)
        layout?.sepiaSwitch?.setOnCheckedChangeListener(this)
        layout?.grayscaleSwitch?.setOnCheckedChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        when (seekBar?.id) {
            R.id.seekBarBright -> if (FilterHelper.isContains(filters, "br")) {
                val params: ArrayList<Float> = ArrayList()
                params.add(progress.toFloat() / 50f)
                FilterHelper.findFilterId(filters, "br").params = params
            } else
                filters.add(Filter("br", progress.toFloat() / 50f))

            R.id.seekBarContrast ->  if (FilterHelper.isContains(filters, "ct")) {
                val params: ArrayList<Float> = ArrayList()
                params.add(progress.toFloat() / 50f)
                FilterHelper.findFilterId(filters, "ct").params = params
            } else
                filters.add(Filter("ct", progress.toFloat() / 50f))

            R.id.seekBarFilllight -> if (FilterHelper.isContains(filters, "fl")) {
                val params: ArrayList<Float> = ArrayList()
                params.add(progress.toFloat() / 50f)
                FilterHelper.findFilterId(filters, "fl").params = params
            } else
                filters.add(Filter("fl", progress.toFloat() / 50f))

            R.id.seekBarFishEye -> if (FilterHelper.isContains(filters, "fe")) {
                val params: ArrayList<Float> = ArrayList()
                params.add(progress.toFloat() / 50f)
                FilterHelper.findFilterId(filters, "fe").params = params
            } else
                filters.add(Filter("fe", progress.toFloat() / 50f))

            R.id.seekBarTemperature -> if (FilterHelper.isContains(filters, "tr")) {
                val params: ArrayList<Float> = ArrayList()
                params.add(progress.toFloat() / 50f)
                FilterHelper.findFilterId(filters, "tr").params = params
            } else
                filters.add(Filter("tr", progress.toFloat() / 50f))
        }
        (activity as MainActivity).glRenderEvent(filters)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {/*Not implemented*/}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {/*Not implemented*/}

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {/*Not implemented*/}
}

