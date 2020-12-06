package tech.hanwool.caraccount.api.model

import com.google.gson.annotations.SerializedName


data class AveragePriceInMetropolitan(
    @SerializedName("SIDOCD")
    val metropolitanCode: Int,
    @SerializedName("SIDONM")
    val metropolitanName: String,
    @SerializedName("PRODCD")
    val productCode: String,
    @SerializedName("PRICE")
    val price: Float,
    @SerializedName("DIFF")
    val diff: Int
)
