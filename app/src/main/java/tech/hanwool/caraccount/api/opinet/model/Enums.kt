package tech.hanwool.caraccount.api.opinet.model

import com.google.gson.annotations.SerializedName

enum class FuelTypeCode(value: String) {
    @SerializedName("B034")
    HIGH_OCTANE_GASOLINE("B034"),
    @SerializedName("B027")
    GASOLINE("B027"),
    @SerializedName("D047")
    DIESEL("D047"),
    @SerializedName("K015")
    LPG("K015")
}


enum class BrandCode(value: Int) {
    @SerializedName("SKE")
    SKE(0),
    @SerializedName("GSC")
    GSC(1),
    @SerializedName("HDO")
    HDO(2),
    @SerializedName("SOL")
    SOL(3),
    @SerializedName("RTO")
    RTO(4),
    @SerializedName("RTX")
    RTX(5),
    @SerializedName("NHO")
    NHO(6),
    @SerializedName("ETC")
    ETC(7),
    @SerializedName("EiG")
    E1G(8),
    @SerializedName("SKG")
    SKG(9)
}

enum class AroundStationsSort(value: Int) {
    @SerializedName("1")
    PRICE(1),
    @SerializedName("2")
    DISTANCE(2)
}

enum class BooleanEnum(value: String) {
    @SerializedName("Y")
    TRUE("Y"),
    @SerializedName("N")
    FALSE("N")
}