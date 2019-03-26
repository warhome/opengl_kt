package com.misaka.huesapp.view

import android.content.Context
import android.util.DisplayMetrics
import android.widget.RelativeLayout

class ResizeUtils {

    companion object {
        // Set height and width for OpenGL layout, depending on image parameters
        fun resizeView(relativeLayout: RelativeLayout, imageHeight: Float, imageWidth: Float, context: Context) {
            val displayH = context.resources.displayMetrics.heightPixels.toFloat()
            val displayW = context.resources.displayMetrics.widthPixels.toFloat()

            // OpenGL layout should not occupy more than 70% of the screen
            val maximumH = displayH * 0.7f
            val coeff: Float = imageHeight / imageWidth
            //  if(imageHeight > imageWidth) coeff = imageHeight / imageWidth;
            //  else coeff = imageWidth / imageHeight;
            var viewW = displayW
            var viewH = coeff * displayW
            if (viewH > maximumH) {
                viewH = maximumH
                if (imageHeight == imageWidth)
                    viewW = viewH
                else
                    viewW = viewH / coeff
            }
            relativeLayout.layoutParams.width = viewW.toInt()
            relativeLayout.layoutParams.height = viewH.toInt()
        }

        fun convertDpToPixel(dp: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }

        fun convertPixelsToDp(px: Float, context: Context): Float {
            return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

    //    public void resizeView(RelativeLayout relativeLayout ,float imageHeight, float imageWidth, Context context){
    //        DisplayMetrics screenDpi = context.getResources().getDisplayMetrics();
    //
    //        float dpiImageHeight = convertPixelsToDp(imageHeight, context);
    //        float dpiImageWidth = convertPixelsToDp(imageWidth, context);
    //        float resizeCoeff = dpiImageHeight / dpiImageWidth;
    //
    //        float viewW = screenDpi.densityDpi;
    //        float viewH = resizeCoeff * screenDpi.densityDpi;
    //
    //        relativeLayout.getLayoutParams().height = (int)convertDpToPixel(viewH, context);
    //    }

}