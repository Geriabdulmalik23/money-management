package com.geriabdulmalik.moneymanagement.presentation.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geriabdulmalik.moneymanagement.R
import com.geriabdulmalik.moneymanagement.databinding.ActivityOnboardingBinding
import com.geriabdulmalik.moneymanagement.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {

            val intent = Intent(this@OnboardingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

}