package tech.hanwool.caraccount.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import tech.hanwool.caraccount.database.model.CarAndAccountLogs

@Dao
interface CarDao {
    @Transaction
    @Query("SELECT * FROM car WHERE regNum = :regNum")
    fun getCar(regNum: String): CarAndAccountLogs
}