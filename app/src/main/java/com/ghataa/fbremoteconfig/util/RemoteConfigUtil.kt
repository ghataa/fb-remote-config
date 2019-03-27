package com.ghataa.fbremoteconfig.util

import android.util.Log
import com.ghataa.fbremoteconfig.BuildConfig
import com.ghataa.fbremoteconfig.R
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import io.reactivex.Completable

class RemoteConfigUtil {

    companion object {

        const val MILLENNIUM_FALCON_TARGET_KEY = "millennium_falcon_target"
        const val MILLENNIUM_FALCON_TARGET_DEFAULT_VALUE = "tatooine"

        @JvmStatic
        fun init(): Completable {
            return Completable.create { emitter ->
                val remoteConfig = FirebaseRemoteConfig.getInstance()

                val configSettings = FirebaseRemoteConfigSettings.Builder()
                        .setDeveloperModeEnabled(BuildConfig.DEBUG)
                        .build()

                remoteConfig.setConfigSettings(configSettings)
                remoteConfig.setDefaults(R.xml.remote_config_defaults)

                emitter.onComplete()
            }.andThen(fetchRemoteConfigParameters(FirebaseRemoteConfig.getInstance()))
        }

        fun fetchRemoteConfigParameters(remoteConfig: FirebaseRemoteConfig): Completable {
            return Completable.create { emitter ->

                val cacheExpiration: Long = if (remoteConfig.info.configSettings.isDeveloperModeEnabled) 0 else 3600

                remoteConfig.fetch(cacheExpiration)
                        .addOnCompleteListener { task ->
                            run {
                                if (task.isSuccessful) {
                                    Log.d("RemoteConfigUtil", "Fetched")

                                    remoteConfig.activateFetched()
                                    Log.d("RemoteConfigUtil", "Activated")

                                    emitter.onComplete()
                                } else {
                                    Log.d("RemoteConfigUtil", "Fetch Failed")

                                    emitter.onError(task.exception!!)
                                }
                            }
                        }
            }
        }

        @JvmStatic
        fun targetIsTatooine(): Boolean {
            return FirebaseRemoteConfig.getInstance().getString(MILLENNIUM_FALCON_TARGET_KEY)
                    .equals(MILLENNIUM_FALCON_TARGET_DEFAULT_VALUE)
        }
    }
}
