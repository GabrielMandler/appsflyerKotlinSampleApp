package com.appsflyer.kotlinsampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.AppsFlyerLibCore
import kotlinx.android.synthetic.main.activity_main.*

class AFApplication : AppCompatActivity() {
        val logTag = AppsFlyerLibCore.LOG_TAG;
        val devKey = "pJtNoWRvepn9EBtYG4jAUQ";

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val deepLinkDataString: ArrayList<String> = ArrayList()
            val oaoaDataString: ArrayList<String> = ArrayList()

            simpleInAppEventsBtn.setOnClickListener {
                AppsFlyerLib.getInstance().trackEvent(applicationContext, "Sample Event", emptyMap())
            }

            customInAppEventsBtn.setOnClickListener {
                startActivity(Intent(this@AFApplication, CustomInAppEventsActivity::class.java))
            }


            AppsFlyerLib.getInstance().init(devKey, object : AppsFlyerConversionListener {

                override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
                    Log.i(logTag, "[onAppOpenAttribution]")
                    data?.map {
                        Log.d(BuildConfig.FLAVOR, "${it.key} : ${it.value}")
                        oaoaDataString.add( it.key + " " + it.value + "\n")
                    }
                    oaoaDataStr.text = oaoaDataString.joinToString()
                }

                override fun onAttributionFailure(error: String?) {
                    Log.e(logTag, "[onAttributionFailure] $error")
                }

                override fun onInstallConversionDataLoaded(data: MutableMap<String, String>?) {
                    Log.i(logTag, "[onInstallConversionDataLoaded]")
                    data?.let { cvData ->
                        cvData.map {
                            Log.i(BuildConfig.FLAVOR, "${it.key} : ${it.value}")
                            deepLinkDataString.add( it.key + " " + it.value + "\n")
                        }
                        deepLinkDataStr.text = deepLinkDataString.joinToString()

                        /****************** Deferred Deep Link ******************/

                        /****************** If it's first open ******************/
                        if (cvData["is_first_launch"] == "true") { //If it's first open
                            /****************** Check if it's from deferred deep linking ******************/
                            if (cvData["af_adset"] == "deferreddeeplink") { // Assume af_adset is used for passing deferred deep link info.
                                Log.d(logTag, cvData["af_ad"])
                            }
                        }
                    }
                }

                override fun onInstallConversionFailure(error: String?) {
                    Log.e(logTag, "[onAttributionFailure] $error")
                }
            })
            AppsFlyerLib.getInstance().setDebugLog(true)
            AppsFlyerLib.getInstance().startTracking(application)
        }

}
