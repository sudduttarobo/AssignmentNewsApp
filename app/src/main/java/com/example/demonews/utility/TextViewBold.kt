package com.example.demonews.utility

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.demonews.appdata.AppData.Companion.getTypefaceBold

/**
 * Created by Sudipta Dutta
 */
class TextViewBold : AppCompatTextView {
    constructor(context: Context?) : super(context!!) {
        typeface = getTypefaceBold(getContext())
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        typeface = getTypefaceBold(getContext())
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
        typeface = getTypefaceBold(getContext())
    }
}