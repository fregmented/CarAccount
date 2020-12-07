package tech.hanwool.caraccount.api.navermap.model

data class ReverseGeoCodingResult (
    val status: Status,
    val results: List<Result>
) {
    data class Status(
        val code: Int,
        val name: String,
        val message: String
    )
    data class Result(
        val name: String,
        val code: Code,
        val region: Region
    ) {
        data class Code(
            val id: String,
            val type: String,
            val mappingId: String
        )
        data class Region(
            val area0: Area,
            val area1: Area,
            val area2: Area?,
            val area3: Area?,
            val area4: Area?,
            val area5: Area?,
        ) {
            data class Area(
                val name: String,
            ) {
                data class Coord(
                    val center: Center
                ) {
                    data class Center(
                        val crs: String,
                        val x: Double,
                        val y: Double,
                    )
                }
            }
        }
    }
}