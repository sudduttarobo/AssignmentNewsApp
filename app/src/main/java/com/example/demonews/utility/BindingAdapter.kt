package com.example.demonews.utility

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.demonews.R

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

/*@BindingAdapter("mutableSwitch")
fun setMutableSwitch(view: Switch, isSwitch : MutableLiveData<Boolean>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && isSwitch != null) {
        isSwitch.observe(parentActivity, Observer { value -> view.isChecked = value ?: false })
    }
}*/

@BindingAdapter("mutableImage")
fun setMutableImageView(view: ImageView, url: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null && url != null) {
        url.observe(parentActivity, Observer { value ->

            Glide.with(parentActivity)
                .load(value)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(view)

            /*Glide.with(parentActivity)
                .load(value)
                .centerCrop()
                .placeholder(R.drawable.ic_profile_image)
                .error(R.drawable.ic_profile_image)
                .fallback(R.drawable.ic_profile_placeholder)
                .into(view)*/
        })
    }
}