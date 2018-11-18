package com.abc40.guide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.abc40.guide.map.LocationHelper
import com.abc40.guide.map.MapHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mapHelper: MapHelper
    lateinit var locationHelper:LocationHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapHelper = MapHelper(applicationContext, mapView.map)
        locationHelper = LocationHelper(this)
        locationHelper.addCallback(mapHelper)
        locationHelper.start()
    }

    override fun onRestart() {
        super.onRestart()
        locationHelper.start()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        locationHelper.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}
