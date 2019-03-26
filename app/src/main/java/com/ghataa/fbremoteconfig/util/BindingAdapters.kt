package com.ghataa.fbremoteconfig.util

import android.databinding.BindingAdapter
import android.widget.Button
import com.ghataa.fbremoteconfig.R
import com.ghataa.fbremoteconfig.data.FalconTarget

object BindingAdapters {

    @BindingAdapter("app:targetText")
    @JvmStatic
    fun targetText(button: Button, target: FalconTarget) {
        when (target) {
            FalconTarget.TATOOINE -> button.text = button.context.getString(R.string.tatooine)
            FalconTarget.DEATH_STAR -> button.text = button.context.getString(R.string.death_star)
        }
    }
}
