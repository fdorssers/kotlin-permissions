package com.example.kotlinpermissions

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import permissions.dispatcher.*


@RuntimePermissions
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById(R.id.btn_camera).setOnClickListener {
            //            MainActivityPermissionsDispatcher.showCameraWithCheck(this)
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun showCamera() {
        Toast.makeText(this, "Showing camera", Toast.LENGTH_SHORT).show()
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun showRationaleForCamera(request: PermissionRequest) {
        AlertDialog.Builder(this)
                .setMessage("Requesting camera")
                .setPositiveButton("Allow", { dialog, button -> request.proceed() })
                .setNegativeButton("Deny", { dialog, button -> request.cancel() })
                .show()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun showDeniedForCamera() {
        Toast.makeText(this, "Camera denied", Toast.LENGTH_SHORT).show()
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun showNeverAskForCamera() {
        Toast.makeText(this, "Camera always denied", Toast.LENGTH_SHORT).show()
    }
}
