package tech.hanwool.caraccount.api.model

import com.google.gson.annotations.SerializedName

enum class ProdCode {
    @SerializedName("B034")
    HIGH_OCTANE_GASOLINE,
    @SerializedName("B027")
    GASOLINE,
    @SerializedName("D047")
    DIESEL,
    @SerializedName("C004")
    KEROSENE,
    @SerializedName("K012")
    CAR_BUTANE
}