package com.appsflyer.kotlinsampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.AppsFlyerLibCore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        simpleInAppEventsBtn.setOnClickListener {
            AppsFlyerLib.getInstance().trackEvent(this@MainActivity, "Sample Event", emptyMap())
        }

        customInAppEventsBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, CustomInAppEventsActivity::class.java))
        }
    }
}

/* todo
2. UI
6. seperate start tracking

*/