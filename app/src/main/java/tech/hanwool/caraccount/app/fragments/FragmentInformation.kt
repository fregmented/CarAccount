package tech.hanwool.caraccount.app.fragments

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import tech.hanwool.caraccount.R
import tech.hanwool.caraccount.api.navermap.NaverMapApiClient
import tech.hanwool.caraccount.api.navermap.model.Coordinate
import tech.hanwool.caraccount.api.opinet.OpinetApiClient
import tech.hanwool.caraccount.api.opinet.model.FuelTypeCode
import tech.hanwool.caraccount.databinding.FragmentInformationBinding

class FragmentInformation: BaseFragment<FragmentInformationBinding>() {

    var locationManager: LocationManager? = null

    override fun getLayoutId(): Int = R.layout.fragment_information

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.fragment = this
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
        loadAllAreaFuelPrice()
        loadLocalFuelPrice()
    }

    override fun onPause() {
        super.onPause()
        locationManager?.removeUpdates(locationChangeListener)
    }

    override fun onStop() {
        super.onStop()
    }

    private fun loadAllAreaFuelPrice() {
        OpinetApiClient.client.getAvrgPriceAll()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val fuelType: FuelTypeCode = FuelTypeCode.valueOf(context?.getSharedPreferences(getString(R.string.pref_main), Context.MODE_PRIVATE)
                    ?.getString(getString(R.string.pref_fuel_type), FuelTypeCode.LPG.name) ?: FuelTypeCode.GASOLINE.name)
                val fuelCode = fuelType.javaClass.getField(fuelType.name)
                    .getAnnotation(SerializedName::class.java)?.value

                mBinding?.fuelType?.text = resources.getStringArray(R.array.fuel_type_name)[fuelType.ordinal]
                val price = it.result.oil.find {
                    Log.i(TAG, "it: ${it.productCode}, $fuelCode")
                    it.productCode == fuelCode
                }
                Log.i(TAG, "PRICE: $price")
                mBinding?.allAreaPrice = price
            }
    }

    private fun loadLocalFuelPrice() {
        if(!checkPermission()) {
            locationManager =
                context?.applicationContext?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            mBinding?.locationString = "서울 금천구"
            if (checkGpsAndEnable(locationManager!!)) {
                locationManager!!.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    100f,
                    locationChangeListener
                )
                locationManager!!.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    0,
                    100f,
                    locationChangeListener
                )
            }
        }
    }

    private fun checkGpsAndEnable(locationManager: LocationManager): Boolean {
        if(!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
                addCategory(Intent.CATEGORY_DEFAULT)
            })
            return false
        }
        return true
    }

    fun checkPermission(): Boolean =
        ActivityCompat.checkSelfPermission(
            context?.applicationContext!!,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context?.applicationContext!!,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED

    val locationChangeListener: LocationListener = LocationListener {
        Log.e(TAG, "LOCATION CHANGED: $it")
        NaverMapApiClient.client.reverseGeoCoding(Coordinate(it.latitude, it.longitude))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess{ result ->
                Log.w(TAG, "REVERSE_GEO: $result")
            }
            .doOnError{ th ->
                Log.e(TAG, "REVERSE_GEO ERROR", th)
            }
    }
    companion object{
        val TAG = FragmentInformation::class.java.simpleName
    }
}