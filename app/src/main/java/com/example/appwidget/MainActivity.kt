package com.example.appwidget

import android.os.Bundle
import android.widget.AdapterViewFlipper
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @DrawableRes
    var galleryId: ArrayList<Int> =
        arrayListOf(R.drawable.t2, R.drawable.t3, R.drawable.t4)
    lateinit var af: AdapterViewFlipper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setViewFlip()
        setOnClickEvent()

    }

    override fun onResume() {
        super.onResume()
        btn_toggle?.let {
            if (btn_toggle.isChecked) af.startFlipping() else af.stopFlipping()
        }

    }

    private fun setListItem() {
        for (i in 1..5) {
            galleryId.add(resources.getIdentifier('t' + i.toString(), "drawble", this.packageName))
        }
    }


    private fun setViewFlip() {
        af = avf_container.apply {
            adapter = GalleryAdapter(this@MainActivity, galleryId)
        }

    }

    private fun setOnClickEvent() {
        btn_before.setOnClickListener {
            af.showPrevious()
        }

        btn_after.setOnClickListener {
            af.showNext()

        }

        btn_toggle.setOnClickListener {
            if (btn_toggle.isChecked) af.startFlipping() else af.stopFlipping()
        }
    }


}