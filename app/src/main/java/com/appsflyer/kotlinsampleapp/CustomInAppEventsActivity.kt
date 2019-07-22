package com.appsflyer.kotlinsampleapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.appsflyer.AppsFlyerLib

class CustomInAppEventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_event)
        var key = findViewById(R.id.inAppKey) as EditText
        var value = findViewById(R.id.inAppValue) as EditText
        var sendEvent = findViewById(R.id.sendEventBtn) as Button

        sendEvent.setOnClickListener {
            var keyStr = key.text
            var valueStr = value.text
            val pairsValue = HashMap<String, Any>()
            pairsValue.put(keyStr.toString(), valueStr.toString())
            AppsFlyerLib.getInstance().trackEvent(applicationContext, "In app custom event", pairsValue)
        }
    }
}
