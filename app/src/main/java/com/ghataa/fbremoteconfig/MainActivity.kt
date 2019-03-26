package com.ghataa.fbremoteconfig

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ghataa.fbremoteconfig.data.FalconTarget
import com.ghataa.fbremoteconfig.databinding.ActivityMainBinding
import com.ghataa.fbremoteconfig.data.MainViewModel
import com.ghataa.fbremoteconfig.util.RemoteConfigUtil

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel

        viewModel.setButtonText(if (RemoteConfigUtil.targetIsTatooine()) FalconTarget.TATOOINE else FalconTarget.DEATH_STAR)
    }
}
