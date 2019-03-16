package com.ghataa.fbremoteconfig

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ghataa.fbremoteconfig.databinding.ActivityMainBinding
import com.ghataa.fbremoteconfig.util.Handlers
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class MainActivity : AppCompatActivity() {

    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.handlers = Handlers()

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
        val cacheExpiration: Long = if (remoteConfig.info.configSettings.isDeveloperModeEnabled) {
            0
        } else {
            3600
        }

        remoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Fetched", Toast.LENGTH_SHORT).show()
                        remoteConfig.activateFetched()
                    } else {
                        Toast.makeText(this, "Fetch Failed", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}
