package tech.hanwool.caraccount.api.navermap.model

data class GeoCodingResult (
    val status: String,
    val meta: MetaData,
    val addresses: List<Address>
) {
    data class MetaData(
        val totalCount: Int,
        val page: Int,
        val count: Int
    )
    data class Address(
        val roadAddress: String,
        val jibunAddress: String,
        val englishAddress: String,
        val addressElements: List<Element>,
        val x: Double,
        val y: Double,
        val distance: Double
    ) {
        data class Element(
            val types: List<String>?,
            val longName: String?,
            val shortName: String?,
            val code: String?
        )
    }
}