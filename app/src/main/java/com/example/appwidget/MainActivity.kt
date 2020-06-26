package com.example.appwidget

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.AdapterViewFlipper
import android.widget.Toast
import androidx.annotation.DrawableRes
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : PermissionActivity() {

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
            //if (btn_toggle.isChecked) af.startFlipping() else af.stopFlipping()
            openMediaStore()
        }
    }

    private fun openMediaStore() {
        if (haveStoragePermission()) {
            pickImages()
        } else {
            requestPermission()
        }
    }

    private fun pickImages() {
        Intent().apply {
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)  // API 18 이상 사용 가능
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            //action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(this, "Select Picture"), PICK_IMAGE)
        }
    }

    private fun openGallery() {
        val intentGallery = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivity(intentGallery)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            val uriList = mutableListOf<Uri>()

            // select one item
            data?.data?.let {
                uriList.add(it)
            }

            //select multiple item
            data?.clipData?.let {
                for (i in 0 until it.itemCount) {
                    uriList.add(it.getItemAt(i).uri)
                }
            }

            Toast.makeText(this@MainActivity, uriList.toString(), Toast.LENGTH_LONG).show()

            showImages(uriList[0])
        }

    }

    private fun showImages(uri: Uri) {
        img_selected.setImageURI(uri)
    }


    companion object {
        const val PICK_IMAGE = 100
    }
}