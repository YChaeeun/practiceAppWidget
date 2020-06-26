package com.example.appwidget

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

abstract class PermissionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            READ_EXTERNAL_STORAGE_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {  // permission granted
                    showImages()
                } else { // permission denied
                    val showRationale =
                        ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )

                    if (showRationale) {
                        Toast.makeText(
                            this,
                            "설명 : 권한을 허용해줘야 앱을 사용할 수 있어요!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        goToSettings()
                    }
                }
                return
            }
        }
    }

    fun openMediaStore() {
        if (haveStoragePermission()) {
            showImages()
        } else {
            requestPermission()
        }
    }

    private fun goToSettings() {
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName")
        ).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }.also { intent ->
            startActivity(intent)
        }
    }

    // does app still have permission?
    private fun haveStoragePermission() =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PermissionChecker.PERMISSION_GRANTED

    // if not, request permission
    private fun requestPermission() {
        if (!haveStoragePermission()) {
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(
                this, permissions,
                READ_EXTERNAL_STORAGE_REQUEST
            )
        }
    }

    private fun showImages() {
        loadImage()
    }

    private fun loadImage() {
        Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val READ_EXTERNAL_STORAGE_REQUEST = 0x1045
    }

    // loadImage
//    fun loadImages() {
//        viewModelScope.launch {
//            val imageList = queryImages()
//            _images.postValue(imageList)
//
//            if (contentObserver == null) {
//                contentObserver = getApplication<Application>().contentResolver.registerObserver(
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                ) {
//                    loadImages()
//                }
//            }
//        }
//    }


}