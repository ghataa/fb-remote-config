package com.ghataa.fbremoteconfig

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class SplashActivity : AppCompatActivity() {

    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        initFirebaseRemoteConfig()
    }

    fun initFirebaseRemoteConfig() {
        remoteConfig = FirebaseRemoteConfig.getInstance()

        val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build()

        remoteConfig.setConfigSettings(configSettings)
        remoteConfig.setDefaults(R.xml.remote_config_defaults)

        fetchRemoteConfigParameters()
    }

    fun fetchRemoteConfigParameters() {
        val cacheExpiration: Long = if (remoteConfig.info.configSettings.isDeveloperModeEnabled) 0 else 3600

        remoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Fetched", Toast.LENGTH_SHORT).show()

                        remoteConfig.activateFetched()

                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "Fetch Failed", Toast.LENGTH_SHORT).show()
                    }

                    finish()
                }
    }
}