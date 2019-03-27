package com.ghataa.fbremoteconfig

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ghataa.fbremoteconfig.util.RemoteConfigUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private var compositeDisposable = CompositeDisposable()
    private val TIMEOUT_FOR_FETCHING_REMOTE_CONFIG_PARAMS_IN_MILLISECONDS = 5000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val initRemoteConfigDisposable = RemoteConfigUtil.init()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(TIMEOUT_FOR_FETCHING_REMOTE_CONFIG_PARAMS_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
                .subscribe({
                    navigateForward()
                }, {
                    navigateForward()
                })

        compositeDisposable.add(initRemoteConfigDisposable)
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.dispose()
    }

    fun navigateForward() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}