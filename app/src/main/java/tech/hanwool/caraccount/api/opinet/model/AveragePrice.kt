package tech.hanwool.caraccount.api.opinet.model

import com.google.gson.annotations.SerializedName

data class AveragePrice(
    @SerializedName("PRODCD")
    val productCode: FuelTypeCode = FuelTypeCode.GASOLINE,
    @SerializedName("PRICE")
    val price: Double = 0.0,
    @SerializedName("DIFF")
    val diff: Double = 0.0
)
