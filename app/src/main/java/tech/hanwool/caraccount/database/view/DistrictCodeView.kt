package tech.hanwool.caraccount.database.view

import androidx.room.DatabaseView

@DatabaseView("SELECT * FROM district_codes")
data class DistrictCodeView(
        val name: String,
        val code: String
)