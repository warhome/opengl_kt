package com.misaka.huesapp.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.misaka.huesapp.AppConstant
import com.misaka.huesapp.R


class BaseImpl(_context: Context, _activity: Activity): Base {

    private val context: Context = _context
    private val activity: Activity = _activity

    override fun showOpenImageDialog() {
        val builder = AlertDialog.Builder(context)
            .setTitle("Open from")
            .setItems(R.array.options) { _, option ->
                when (option) {
                    AppConstant.OPEN_FROM_CAMERA  -> checkCameraPermission()
                    AppConstant.OPEN_FROM_GALLERY -> showGalleryIntent()
                }
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // TODO: replace by snackBarListener(?)
    override fun checkCameraPermission() {
        Dexter.withActivity(activity)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    dispatchTakePictureIntent()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {

                }

                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {
                }
            }).check()
    }

    override fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity.packageManager)?.also {
                activity.startActivityForResult(takePictureIntent, AppConstant.REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun showGalleryIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"),AppConstant.GALLERY_REQUEST)
    }

    override fun checkWriteDataPermission(): Boolean {
        var isGranted: Boolean = false
        Dexter.withActivity(activity)
            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    isGranted = true
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {

                }

                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {
                }
            }).check()
        return isGranted
    }

}