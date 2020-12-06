package tech.hanwool.caraccount.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class CarAndAccountLogs(
    @Embedded val car: Car,
    @Relation(
        parentColumn = "car",
        entityColumn = "account_log"
    )
    val accountLogs: List<AccountLog>
)
