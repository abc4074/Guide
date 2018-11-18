package com.abc40.guide.map

import android.content.Context
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption

class LocationHelper(context: Context) : BDAbstractLocationListener() {

    val locationClient = LocationClient(context)

    val callbacks : MutableList<BDAbstractLocationListener> = mutableListOf()

    init {
        val option = LocationClientOption()
        option.setCoorType("bd09ll")
        option.setScanSpan(5000)
        option.setIsNeedAddress(true)
        locationClient.locOption = option
        locationClient.registerLocationListener(this)
    }

    override fun onReceiveLocation(bdLocation: BDLocation?) {
        for(callback in callbacks){
            callback.onReceiveLocation(bdLocation)
        }
    }

    fun addCallback(callback: BDAbstractLocationListener){
        callbacks.add(callback)
    }

    fun removeCallback(callback: BDAbstractLocationListener){
        callbacks.remove(callback)
    }

    fun start(){
        locationClient.start()
    }

    fun stop(){
        locationClient.stop()
    }
}