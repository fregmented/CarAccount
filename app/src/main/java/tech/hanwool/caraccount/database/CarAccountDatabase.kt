package tech.hanwool.caraccount.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tech.hanwool.caraccount.R
import tech.hanwool.caraccount.database.dao.AccountLogDao
import tech.hanwool.caraccount.database.dao.CarDao
import tech.hanwool.caraccount.database.dao.DistrictCodeDao
import tech.hanwool.caraccount.database.model.AccountLog
import tech.hanwool.caraccount.database.model.Car
import tech.hanwool.caraccount.database.model.DistrictCode
import tech.hanwool.caraccount.database.util.AccountLogTypeConverter
import tech.hanwool.caraccount.database.util.DateTimeConverter
import tech.hanwool.caraccount.database.util.FuelTypeConverter
import tech.hanwool.caraccount.database.view.AccountLogView
import tech.hanwool.caraccount.database.view.CarView
import tech.hanwool.caraccount.database.view.DistrictCodeView
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type


@Database(entities = [Car::class, AccountLog::class, DistrictCode::class],
        views = [CarView::class, AccountLogView::class, DistrictCodeView::class],
        version = 1)
@TypeConverters(value = [FuelTypeConverter::class, AccountLogTypeConverter::class, DateTimeConverter::class])
abstract class CarAccountDatabase: RoomDatabase() {
    abstract fun carDao(): CarDao
    abstract fun accountLogDao(): AccountLogDao
    abstract fun districtCodeDao(): DistrictCodeDao

    companion object {
        private var INSTANCE: CarAccountDatabase? = null

        fun getInstance(context: Context): CarAccountDatabase {
            if(INSTANCE == null) {
                synchronized(CarAccountDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context, CarAccountDatabase::class.java, "car_account.db").apply {
                                addCallback(object : RoomDatabase.Callback() {
                                    override fun onCreate(db: SupportSQLiteDatabase) {
                                        GlobalScope.launch(Dispatchers.IO){
                                            val reader = BufferedReader(InputStreamReader(context.resources.openRawResource(R.raw.district_code)))
                                            val listType: Type = object : TypeToken<List<DistrictCode>>() {}.type
                                            val codes: List<DistrictCode> = Gson().fromJson(reader, listType)
                                            Log.w("DB_PREPOPULATED", "CODES: $codes")
                                            getInstance(context).districtCodeDao().insertAll(codes)
                                                .subscribeOn(Schedulers.newThread())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .doOnComplete {
                                                    Log.w("DB_PREPOPULATED", "Insert done!")
                                                }
                                        }
                                    }
                                })
                            }.build()
                }
            }
            return INSTANCE!!
        }
    }
}