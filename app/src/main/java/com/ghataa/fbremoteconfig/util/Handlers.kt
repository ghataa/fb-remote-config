package com.ghataa.fbremoteconfig.util

import android.view.View
import android.widget.Toast
import com.ghataa.fbremoteconfig.R
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class Handlers {

    fun onMainButtonClick(view: View) {
        val stringResId = if (FirebaseRemoteConfig.getInstance().getString(RemoteConfigConstants.MILLENNIUM_FALCON_TARGET_KEY)
                        .equals(RemoteConfigConstants.MILLENNIUM_FALCON_TARGET_DEFAULT_VALUE)) {
            R.string.tatooine
        } else {
            R.string.death_star
        }

        Toast.makeText(view.context, stringResId, Toast.LENGTH_SHORT).show()
    }
}
