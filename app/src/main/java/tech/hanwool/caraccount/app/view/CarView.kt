package tech.hanwool.caraccount.app.view

import androidx.room.DatabaseView
import androidx.room.TypeConverters
import tech.hanwool.caraccount.database.model.Car
import tech.hanwool.caraccount.database.util.FuelTypeConverter

@DatabaseView("SELECT * FROM car")
data class Car(
    val regNum: String,
    val vin: String,
    val fuelType: Car.FuelType,
    var memo: String?,
    var cumulativeDistance: Float = 0.0f,
)
