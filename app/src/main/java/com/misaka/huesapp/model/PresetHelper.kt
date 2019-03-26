package com.misaka.huesapp.model

import android.content.Context
import android.graphics.Bitmap
import com.zomato.photofilters.FilterPack

class PresetHelper {
    companion object {
        private var presetList = ArrayList<Preset>()

        fun getPresetList(): ArrayList<Preset> {
            return presetList
        }

        fun createPreviewPreset(lowQualityBitmap: Bitmap, context: Context) {

            if (!presetList.isEmpty()) presetList.clear()

            var workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("Adele", FilterPack.getAdeleFilter(context).processFilter(workedBitmap)))

            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("Amazon", FilterPack.getAmazonFilter(context).processFilter(workedBitmap)))

            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("April", FilterPack.getAprilFilter(context).processFilter(workedBitmap)))

            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("Audrey", FilterPack.getAudreyFilter(context).processFilter(workedBitmap)))

            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("AweStruckVibe", FilterPack.getAweStruckVibeFilter(context).processFilter(workedBitmap)))

            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("BlueMess", FilterPack.getBlueMessFilter(context).processFilter(workedBitmap)))

            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("Clarendon", FilterPack.getClarendon(context).processFilter(workedBitmap)))

            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("Cruz", FilterPack.getCruzFilter(context).processFilter(workedBitmap)))

            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("Haan", FilterPack.getHaanFilter(context).processFilter(workedBitmap)))

            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("LimeStutter", FilterPack.getLimeStutterFilter(context).processFilter(workedBitmap)))

            workedBitmap = lowQualityBitmap.copy(lowQualityBitmap.config, true)
            presetList.add(Preset("Mars", FilterPack.getMarsFilter(context).processFilter(workedBitmap)))
        }
    }
}