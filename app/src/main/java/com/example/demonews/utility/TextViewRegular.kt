package com.example.demonews.utility

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.demonews.appdata.AppData.Companion.getTypefaceRegular

/**
 * Created by Sudipta Dutta
 */
class TextViewRegular : AppCompatTextView {
    constructor(context: Context?) : super(context!!) {
        typeface = getTypefaceRegular(getContext())
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        typeface = getTypefaceRegular(getContext())
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
        typeface = getTypefaceRegular(getContext())
    }
}