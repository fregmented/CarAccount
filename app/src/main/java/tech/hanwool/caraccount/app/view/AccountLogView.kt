package tech.hanwool.caraccount.app.view

import androidx.room.DatabaseView
import tech.hanwool.caraccount.database.model.AccountLog
import java.util.*

@DatabaseView("SELECT * from account_log")
data class AccountLog(
    val timestamp: Date,
    val accountLogType: AccountLog.AccountLogType,
    val cumulativeDistance: Float,
    val unitPrice: Float,
    val amount: Float = 0f,
    val isFull: Boolean = false,
    val memo: String?
)
