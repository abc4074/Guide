package com.abc40.guide.map

import android.content.Context
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MyLocationData
import com.baidu.mapapi.model.LatLng

class MapHelper(val context: Context, val baiduMap: BaiduMap) : BDAbstractLocationListener() {

    var firstLocation = true

    init {
        baiduMap.mapType = BaiduMap.MAP_TYPE_NORMAL
        baiduMap.isMyLocationEnabled = true
    }

    override fun onReceiveLocation(bdLocation: BDLocation?) {
        bdLocation?.let {
            updateLocation(bdLocation)
            if (firstLocation) {
                updateMapStatus(bdLocation)
                firstLocation = false
            }
        }
    }

    fun updateMapStatus(bdLocation: BDLocation) {
        var latLng = LatLng(bdLocation.latitude, bdLocation.longitude)
        var mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng, 16f)
        baiduMap.animateMapStatus(mapStatusUpdate)
    }

    fun updateLocation(bdLocation: BDLocation) {
        var locationBuilder = MyLocationData.Builder().latitude(bdLocation.latitude).longitude(bdLocation.longitude)
        baiduMap.setMyLocationData(locationBuilder.build())
    }
}