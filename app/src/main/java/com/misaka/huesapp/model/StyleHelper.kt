package com.misaka.huesapp.model

import android.content.Context
import android.graphics.BitmapFactory
import com.misaka.huesapp.R

class StyleHelper{
    companion object {
        private var styleList = ArrayList<Transfer>()

        fun getStyleList(): ArrayList<Transfer> {
            return styleList
        }

        fun createStylePreview(context: Context) {

            if (!styleList.isEmpty()) styleList.clear()

            val image = BitmapFactory.decodeResource(context.resources, R.drawable.starry_night)
            styleList.add(Transfer("style0", image))
            styleList.add(Transfer("style1", image))
            styleList.add(Transfer("style2", image))
            styleList.add(Transfer("style3", image))
            styleList.add(Transfer("style4", image))
            styleList.add(Transfer("style5", image))
            styleList.add(Transfer("style6", image))
            styleList.add(Transfer("style7", image))
            styleList.add(Transfer("style8", image))
            styleList.add(Transfer("style9", image))
            styleList.add(Transfer("style10", image))
        }
    }
}