package tech.hanwool.caraccount.api.navermap.model

data class Coordinate(
    val lat: Double,
    val lng: Double
) {
    override fun toString(): String {
        return "$lng,$lat"
    }
}
