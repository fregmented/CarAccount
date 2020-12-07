package tech.hanwool.caraccount.app.model

data class DistrictCode(
    val name: String,
    val code: String
)

data class MetropolitanCode(
    val name: String,
    val code: String,
    val districts: List<DistrictCode>
)