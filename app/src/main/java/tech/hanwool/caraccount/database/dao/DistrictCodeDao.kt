package tech.hanwool.caraccount.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import tech.hanwool.caraccount.database.model.DistrictCode

@Dao
interface DistrictCodeDao {
    @Query("SELECT * FROM district_codes")
    fun getAll(): Observable<List<DistrictCode>>

    @Query("SELECT * FROM district_codes WHERE name = :name")
    fun get(name: String): Single<DistrictCode>

    @Insert
    fun insert(code: DistrictCode): Completable

    @Transaction
    @Insert
    fun insertAll(codes: List<DistrictCode>): Completable
}