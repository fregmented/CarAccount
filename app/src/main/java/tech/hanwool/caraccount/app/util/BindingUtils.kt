package tech.hanwool.caraccount.app.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("android:text")
fun setFloat(view: TextView, value: Float) {
    if(value.isNaN()) view.setText("")
    else view.setText("$value")
}

@InverseBindingAdapter(attribute = "android:text")
fun getFloat(view: TextView): Float {
    val num = view.text.toString()
    return num.toFloatOrNull() ?: 0.0f
}


@BindingAdapter("android:text")
fun setDouble(view: TextView, value: Double) {
    if(value.isNaN()) view.setText("")
    else view.setText("$value")
}

@InverseBindingAdapter(attribute = "android:text")
fun getDouble(view: TextView): Double {
    val num = view.text.toString()
    return num.toDoubleOrNull() ?: 0.0
}