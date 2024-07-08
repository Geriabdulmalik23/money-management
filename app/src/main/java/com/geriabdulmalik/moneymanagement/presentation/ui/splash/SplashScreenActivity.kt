package com.geriabdulmalik.moneymanagement.presentation.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.geriabdulmalik.moneymanagement.R
import com.geriabdulmalik.moneymanagement.presentation.ui.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        object : CountDownTimer(2000, 1000) {

            override fun onFinish() {

                val intent = Intent(this@SplashScreenActivity, OnboardingActivity::class.java)
                startActivity(intent)
                finish()

            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }.start()
    }

}