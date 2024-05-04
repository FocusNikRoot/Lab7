package com.example.yandexmaplab

import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

var key = "d93ad095-e575-437d-b542-1d384a150555"

private lateinit var mapView: MapView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey(key)
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapview)
        val mapKit: MapKit = MapKitFactory.getInstance()
        val jams = mapKit.createTrafficLayer(mapView.mapWindow)

        val btnCity = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val btnPost = findViewById<FloatingActionButton>(R.id.floatingActionButtonPochta)
        val toggleBtn = findViewById<ToggleButton>(R.id.toggleButton)

        btnCity.setOnClickListener {
            mapView.map.move(CameraPosition(Point(findKemerovo()[0], findKemerovo()[1]), 11.0f, 0.0f, 0.0f))
        }

        btnPost.setOnClickListener {
            mapView.map.move(CameraPosition(Point(findGlavPochta()[0], findGlavPochta()[1]), 18.0f, 0.0f, 0.0f))
        }

        toggleBtn.setOnClickListener {
            if(toggleBtn.isChecked){
                jams.isTrafficVisible = true
            } else {
                jams.isTrafficVisible = false
            }
        }
    }

    fun findKemerovo(): DoubleArray{
        val kemerovo = doubleArrayOf(55.3333, 86.0833)
        return kemerovo
    }

    fun findGlavPochta(): DoubleArray{
        val kemerovo = doubleArrayOf(55.354993, 86.0858053)
        return kemerovo
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}