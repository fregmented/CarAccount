package tech.hanwool.caraccount.api.model

import com.google.gson.annotations.SerializedName

data class AveragePriceAll(
    @SerializedName("TRADE_DT")
    val datetime: String,
    @SerializedName("PRODCD")
    val productCode: String,
    @SerializedName("PRODNM")
    val productName: String,
    @SerializedName("PRICE")
    val price: Float,
    @SerializedName("DIFF")
    val diff: Int
)
