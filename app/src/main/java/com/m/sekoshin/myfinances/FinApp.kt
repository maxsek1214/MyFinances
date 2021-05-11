package com.m.sekoshin.myfinances

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.m.sekoshin.myfinances.data.repository.*
import com.m.sekoshin.myfinances.data.room.FinanceDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FinApp : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { FinanceDB.getDataBase(this, applicationScope) }
    val accountTypeRep by lazy { AccountTypeRepository(database.accountTypeDao()) }
    val aliasRep by lazy { AliasRepository(database.aliasDao()) }
    val cashAccountRep by lazy { CashAccountRepository(database.cashAccountDao()) }
    val cashTransactionRep by lazy {
        CashTransactionRepository(
            database.cashTransactionDao(),
            database.sourceRecipientDao(),
            database.cashiersCheckDao(),
            database.flowOfFundDao()
        )
    }
    val goodCategoryRep by lazy { GoodCategoryRepository(database.goodCategoryDao()) }
    val issuerRep by lazy { IssuerRepository(database.issuerDao()) }
    val sourceRecipientRep by lazy { SourceRecipientRepository(database.sourceRecipientDao()) }
    val transactionCategoryRep by lazy { TransactionCategoryRepository(database.transactionCategoryDao()) }

    lateinit var preferenceRepository: PreferenceRepository

    override fun onCreate() {
        super.onCreate()
        preferenceRepository =
            PreferenceRepository(PreferenceManager.getDefaultSharedPreferences(this))
    }

}