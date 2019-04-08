package com.misaka.huesapp.model

import android.content.Context
import android.graphics.Bitmap
import com.zomato.photofilters.FilterPack

class PresetHelper {
    companion object {
        private var presetList = ArrayList<Transfer>()

        fun getPresetList(): ArrayList<Transfer> {
            return presetList
        }

        fun createPreviewPreset(lowQualityBitmap: Bitmap, context: Context) {
            if (!presetList.isEmpty()) presetList.clear()

            var workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("Adele", FilterPack.getAdeleFilter(context).processFilter(workedBitmap)))
            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("Amazon", FilterPack.getAmazonFilter(context).processFilter(workedBitmap)))
            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("April", FilterPack.getAprilFilter(context).processFilter(workedBitmap)))
            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("Audrey", FilterPack.getAudreyFilter(context).processFilter(workedBitmap)))
            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("AweStruckVibe", FilterPack.getAweStruckVibeFilter(context).processFilter(workedBitmap)))
            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("BlueMess", FilterPack.getBlueMessFilter(context).processFilter(workedBitmap)))
            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("Clarendon", FilterPack.getClarendon(context).processFilter(workedBitmap)))
            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("Cruz", FilterPack.getCruzFilter(context).processFilter(workedBitmap)))
            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("Haan", FilterPack.getHaanFilter(context).processFilter(workedBitmap)))
            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("LimeStutter", FilterPack.getLimeStutterFilter(context).processFilter(workedBitmap)))
            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Transfer("Mars", FilterPack.getMarsFilter(context).processFilter(workedBitmap)))
        }
    }
}