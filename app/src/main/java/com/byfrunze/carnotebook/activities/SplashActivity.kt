package com.byfrunze.carnotebook.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.byfrunze.carnotebook.R


class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 1000.toLong()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed(Runnable {
            startActivity(Intent(this, ChooseAutoActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)

    }
}