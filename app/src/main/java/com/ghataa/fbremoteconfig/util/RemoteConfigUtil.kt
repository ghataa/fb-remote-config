package com.ghataa.fbremoteconfig.util

import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class RemoteConfigUtil {

    companion object {
        const val MILLENNIUM_FALCON_TARGET_KEY = "millennium_falcon_target"
        const val MILLENNIUM_FALCON_TARGET_DEFAULT_VALUE = "tatooine"

        @JvmStatic
        fun targetIsTatooine(): Boolean {
            return FirebaseRemoteConfig.getInstance().getString(MILLENNIUM_FALCON_TARGET_KEY)
                    .equals(MILLENNIUM_FALCON_TARGET_DEFAULT_VALUE)
        }
    }
}
