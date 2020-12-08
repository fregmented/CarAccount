package tech.hanwool.caraccount.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import tech.hanwool.caraccount.database.model.DistrictCode

@Dao
interface DistrictCodeDao {
    @Query("SELECT * FROM district_codes")
    fun getAll(): List<DistrictCode>

    @Query("SELECT * FROM district_codes WHERE name = :name")
    fun get(name: String): DistrictCode

    @Insert
    fun insert(code: DistrictCode)

    @Transaction
    @Insert
    fun insertAll(codes: List<DistrictCode>)
}