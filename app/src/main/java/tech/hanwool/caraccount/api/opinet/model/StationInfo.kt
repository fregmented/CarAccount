package tech.hanwool.caraccount.api.opinet.model

import com.google.gson.annotations.SerializedName

/**
 * 지역별 최저가 주유소 정보 엔티티
 * @param code Station UID
 * @param price Unit price of selected fuel
 * @param brand Station brand
 * @param name Station name
 * @param address Station address
 * @param katecX X coordinates in KATEC
 * @param katecY Y coordinates in KATEC
 * @param distance Distance from queried coordinate
 */
data class StationInfo(
    @SerializedName("UNI_ID")
    val code: String,
    @SerializedName("PRICE")
    val price: Float,
    @SerializedName("POLL_DIV_CD")
    val brand: BrandCode,
    @SerializedName("OS_NM")
    val name: String,
    @SerializedName("NEW_ADR")
    val address: String,
    @SerializedName("GIS_X_COOR")
    val katecX: Float,
    @SerializedName("GIS_Y_COOR")
    val katecY: Float,
    @SerializedName("DISTANCE")
    val distance: Int?
)
