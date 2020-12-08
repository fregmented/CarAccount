package tech.hanwool.caraccount.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "district_codes")
data class DistrictCode(
    @PrimaryKey
    val name: String,
    val code: String
)