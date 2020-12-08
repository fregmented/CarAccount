package tech.hanwool.caraccount.database.view

import androidx.room.DatabaseView
import tech.hanwool.caraccount.database.model.Car

@DatabaseView("SELECT * FROM car")
data class CarView(
    val regNum: String,
    val vin: String,
    val fuelType: Car.FuelType,
    var memo: String?,
    var cumulativeDistance: Float = 0.0f,
)
