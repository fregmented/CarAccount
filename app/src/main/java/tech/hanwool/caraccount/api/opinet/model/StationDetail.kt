package tech.hanwool.caraccount.api.opinet.model


import com.google.gson.annotations.SerializedName

data class StationDetail(
    @SerializedName("CAR_WASH_YN")
    val wash: BooleanEnum,
    @SerializedName("CVS_YN")
    val cvs: BooleanEnum,
    @SerializedName("GIS_X_COOR")
    val katecX: Double,
    @SerializedName("GIS_Y_COOR")
    val katecY: Double,
    @SerializedName("KPETRO_YN")
    val kPETROYN: String,
    @SerializedName("LPG_YN")
    val lPGYN: String,
    @SerializedName("MAINT_YN")
    val mAINTYN: String,
    @SerializedName("NEW_ADR")
    val nEWADR: String,
    @SerializedName("OIL_PRICE")
    val oILPRICE: List<OILPRICE>,
    @SerializedName("OS_NM")
    val oSNM: String,
    @SerializedName("POLL_DIV_CO")
    val pOLLDIVCO: String,
    @SerializedName("SIGUNCD")
    val sIGUNCD: String,
    @SerializedName("TEL")
    val tEL: String,
    @SerializedName("UNI_ID")
    val uNIID: String,
    @SerializedName("VAN_ADR")
    val vANADR: String
)