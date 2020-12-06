package tech.hanwool.caraccount.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import tech.hanwool.caraccount.database.util.FuelTypeConverter

/**
 * Car entity.
 *
 * @param regNum The Registration Number of the Car
 * @param vin Vehicle Identification Number of the Car
 * @param memo Car memo
 * @param cumulativeDistance The cumulative distance of the car
 * @version 1
 */
@Entity(tableName = "car")
data class Car(
    @PrimaryKey
    val regNum: String,

    val vin: String,
    @TypeConverters(FuelTypeConverter::class)
    val fuelType: FuelType,
    var memo: String?,
    var cumulativeDistance: Float = 0.0f,
) {
    /**
     * Fuel type enum.
     */
    enum class FuelType(value: Int) {
        GASOLINE(0),
        HO_GASOLINE(1), // High octane gasoline
        DIESEL(2),
        LPG(3),
        ELECTRICITY(4),
    }
}
