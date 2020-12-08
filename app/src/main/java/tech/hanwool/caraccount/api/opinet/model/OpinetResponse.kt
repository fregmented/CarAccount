package tech.hanwool.caraccount.api.opinet.model

import com.google.gson.annotations.SerializedName

data class OpinetResponse<T> (
    @SerializedName("RESULT")
    val result: Oil<T>
)

data class Oil<T> (
    @SerializedName("OIL")
    val oil: List<T>
)