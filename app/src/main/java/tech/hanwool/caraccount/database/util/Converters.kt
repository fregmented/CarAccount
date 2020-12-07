package tech.hanwool.caraccount.database.util

import androidx.room.TypeConverter
import tech.hanwool.caraccount.database.model.AccountLog
import tech.hanwool.caraccount.database.model.Car
import java.util.*

abstract class IntEnumConverter<T : Enum<T>> {
    inline fun <E : Enum<E>> E.toInt(): Int = this.ordinal
    inline fun <reified E : Enum<E>> Int.toIntEnum(): E = enumValues<E>()[this]

    @TypeConverter
    abstract fun intEnumToInt(value: T): Int
    @TypeConverter
    abstract fun intToEnumInt(value: Int): T
}

class FuelTypeConverter: IntEnumConverter<Car.FuelType>() {
    @TypeConverter
    override fun intEnumToInt(value: Car.FuelType): Int = value.toInt()
    @TypeConverter
    override fun intToEnumInt(value: Int): Car.FuelType = value.toIntEnum()
}

class AccountLogTypeConverter: IntEnumConverter<AccountLog.AccountLogType>() {
    @TypeConverter
    override fun intEnumToInt(value: AccountLog.AccountLogType): Int = value.toInt()
    @TypeConverter
    override fun intToEnumInt(value: Int): AccountLog.AccountLogType = value.toIntEnum()
}

class DateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}