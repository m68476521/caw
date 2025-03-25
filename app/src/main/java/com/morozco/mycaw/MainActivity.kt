package com.morozco.mycaw

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.morozco.mycaw.databinding.ActivityMainBinding
import com.morozco.mycaw.network.ApiManager
import com.morozco.mycaw.network.Status
import com.morozco.mycaw.viewModel.CountryViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val model by viewModels<CountryViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiManager.createApi()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            model.isLoading.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { status ->
                    if (status == Status.IN_PROGRESS) {
                        binding.progressLoader.visibility = View.VISIBLE
                    } else {
                        binding.progressLoader.visibility = View.GONE
                    }
                }
        }
    }
}