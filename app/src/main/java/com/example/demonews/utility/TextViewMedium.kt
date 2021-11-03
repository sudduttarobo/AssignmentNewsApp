package com.example.demonews.utility

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.demonews.appdata.AppData.Companion.getTypefaceMedium

/**
 * Created by Sudipta Dutta
 */
class TextViewMedium : AppCompatTextView {
    constructor(context: Context?) : super(context!!) {
        typeface = getTypefaceMedium(getContext())
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        typeface = getTypefaceMedium(getContext())
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
        typeface = getTypefaceMedium(getContext())
    }
}