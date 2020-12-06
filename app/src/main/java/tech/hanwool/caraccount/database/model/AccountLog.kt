package tech.hanwool.caraccount.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import tech.hanwool.caraccount.database.util.AccountLogTypeConverter
import tech.hanwool.caraccount.database.util.DateTimeConverter
import java.util.*

/**
 * AccountLog entity.
 * Logging payment about fueling, maintenance, buy gadgets
 *
 * @param timestamp Timestamp of created time
 * @param accountLogType What user paid for
 * @param cumulativeDistance Current cumulative drive distance in km
 * @param unitPrice Unit price
 * @param amount Amount
 * @param isFull If User paid fueling, fueling to full of tank
 * @param memo memo
 * @version 1
 */
@Entity(tableName = "account_log")
data class AccountLog(
    @PrimaryKey
    @TypeConverters(DateTimeConverter::class)
    val timestamp: Date,
    @TypeConverters(AccountLogTypeConverter::class)
    val accountLogType: AccountLogType,
    val cumulativeDistance: Float,
    val unitPrice: Float,
    val amount: Float = 0f,
    val isFull: Boolean = false,
    val memo: String?,
    ) {
    enum class AccountLogType(value: Int) {
        FUELING(0),
        FUEL_ADDON(1),
        MAINTENANCE(2),
        GADGETS(3)
    }
}
