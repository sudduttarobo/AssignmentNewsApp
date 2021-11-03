package com.example.demonews.appdata

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat


/*fun Any.log() {
    // if (BuildConfig.DEBUG)
    Log.e("SUDIPTA", this.toString())
}*/

class AppData {

    companion object {

        @JvmStatic
        fun getTypefaceRegular(context: Context): Typeface? {
            var typefaceRegular: Typeface? = null
            if (typefaceRegular == null) {
                typefaceRegular =
                    ResourcesCompat.getFont(context, com.example.demonews.R.font.opensans_regular)

            }
            return typefaceRegular
        }

        @JvmStatic
        fun getTypefaceMedium(context: Context): Typeface? {
            var typefaceMedium: Typeface? = null
            if (typefaceMedium == null) {
                typefaceMedium =
                    ResourcesCompat.getFont(context, com.example.demonews.R.font.opensans_semibold)
            }
            return typefaceMedium
        }

        @JvmStatic
        fun getTypefaceBold(context: Context): Typeface? {
            var typefaceBold: Typeface? = null
            if (typefaceBold == null) {
                typefaceBold =
                    ResourcesCompat.getFont(context, com.example.demonews.R.font.opensans_bold)
            }
            return typefaceBold
        }

        @JvmStatic
        fun getTypefaceLight(context: Context): Typeface? {
            var typefaceBold: Typeface? = null
            if (typefaceBold == null) {
                typefaceBold =
                    ResourcesCompat.getFont(context, com.example.demonews.R.font.opensans_light)
            }
            return typefaceBold
        }
    }
}
