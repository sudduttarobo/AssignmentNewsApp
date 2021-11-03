package com.example.demonews.utility

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.demonews.appdata.AppData.Companion.getTypefaceLight

/**
 *  Created by Sudipta Dutta
 */
class TextViewLight : AppCompatTextView {
    constructor(context: Context?) : super(context!!) {
        typeface = getTypefaceLight(getContext())
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        typeface = getTypefaceLight(getContext())
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
        typeface = getTypefaceLight(getContext())
    }
}