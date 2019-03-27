package com.ghataa.fbremoteconfig

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ghataa.fbremoteconfig.util.RemoteConfigUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SplashActivity : AppCompatActivity() {

    private var compositDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val initRemoteConfigDisposable = RemoteConfigUtil.init()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    navigateForward()
                }, {
                    navigateForward()
                })

        compositDisposable.add(initRemoteConfigDisposable)
    }

    override fun onPause() {
        super.onPause()
        compositDisposable.dispose()
    }

    fun navigateForward() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}