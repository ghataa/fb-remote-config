package com.ghataa.fbremoteconfig

import android.view.View
import android.widget.Toast

class Handlers {

    fun onMainButtonClick(view: View) {
        Toast.makeText(view.context, "onMainButtonClick", Toast.LENGTH_SHORT).show()
    }
}
