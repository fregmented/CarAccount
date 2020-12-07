package tech.hanwool.caraccount.api.opinet.model


import com.google.gson.annotations.SerializedName

data class OILPRICE(
    @SerializedName("PRICE")
    val pRICE: Int,
    @SerializedName("PRODCD")
    val pRODCD: String,
    @SerializedName("TRADE_DT")
    val tRADEDT: String,
    @SerializedName("TRADE_TM")
    val tRADETM: String
)