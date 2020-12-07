package tech.hanwool.caraccount.api.opinet.model

import com.google.gson.annotations.SerializedName


data class AveragePriceInDistrict(
    @SerializedName("SIGUNCD")
    val districtCode: Int,
    @SerializedName("SIGUNNM")
    val districtName: String,
    @SerializedName("PRODCD")
    val productCode: String,
    @SerializedName("PRICE")
    val price: Float,
    @SerializedName("DIFF")
    val diff: Int
)
