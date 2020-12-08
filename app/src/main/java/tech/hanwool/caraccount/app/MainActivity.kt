package tech.hanwool.caraccount.app

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import tech.hanwool.caraccount.R
import tech.hanwool.caraccount.database.CarAccountDatabase
import tech.hanwool.caraccount.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    var mBinding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CarAccountDatabase.getInstance(this)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Dexter
            .withContext(this)
            .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            .withListener(
                DialogOnAnyDeniedMultiplePermissionsListener.Builder
                .withContext(this)
                .withTitle("Camera & audio permission")
                .withMessage("Both camera and audio permission are needed to take pictures of your cat")
                .withButtonText(android.R.string.ok)
                .withIcon(R.mipmap.ic_launcher)
                .build())
            .check()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}