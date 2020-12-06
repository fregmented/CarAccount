package tech.hanwool.caraccount.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.hanwool.caraccount.app.view.AccountLogView
import tech.hanwool.caraccount.app.view.CarView
import tech.hanwool.caraccount.database.dao.AccountLogDao
import tech.hanwool.caraccount.database.dao.CarDao
import tech.hanwool.caraccount.database.model.AccountLog
import tech.hanwool.caraccount.database.model.Car
import tech.hanwool.caraccount.database.util.AccountLogTypeConverter
import tech.hanwool.caraccount.database.util.DateTimeConverter
import tech.hanwool.caraccount.database.util.FuelTypeConverter

@Database(entities = [Car::class, AccountLog::class],
    views = [CarView::class, AccountLogView::class],
    version = 1)
@TypeConverters(value=[FuelTypeConverter::class, AccountLogTypeConverter::class, DateTimeConverter::class])
abstract class CarAccountDatabase: RoomDatabase() {
    abstract fun carDao(): CarDao
    abstract fun accountLogDao(): AccountLogDao

    companion object {
        private var INSTANCE: CarAccountDatabase? = null

        fun getInstance(context: Context): CarAccountDatabase {
            if(INSTANCE == null) {
                synchronized(CarAccountDatabase::class.java) {
                    INSTANCE =
                        Room.databaseBuilder(context, CarAccountDatabase::class.java, "car_account.db")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}