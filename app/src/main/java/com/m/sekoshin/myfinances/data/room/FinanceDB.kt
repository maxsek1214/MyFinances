package com.m.sekoshin.myfinances.data.room

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.m.sekoshin.myfinances.R
import com.m.sekoshin.myfinances.data.room.converter.Converters
import com.m.sekoshin.myfinances.data.room.dao.*
import com.m.sekoshin.myfinances.data.room.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Database(
        entities = [AccountTypeEntity::class, AliasEntity::class, CardTypeEntity::class,
            CashAccountEntity::class, CashiersCheckEntity::class, CashTransactionEntity::class,
            FlowOfFundEntity::class, GoodEntity::class, GoodCategoryEntity::class, IssuerEntity::class,
            PurchaseEntity::class, SourceRecipientEntity::class, StoreEntity::class,
            TransactionCategoryEntity::class], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FinanceDB : RoomDatabase() {

    abstract fun accountTypeDao(): AccountTypeDao
    abstract fun aliasDao(): AliasDao
    abstract fun cardTypeDao(): CardTypeDao
    abstract fun cashAccountDao(): CashAccountDao
    abstract fun cashAccountFlowOfFundDao(): CashAccountFlowOfFundDao
    abstract fun cashTransactionDao(): CashTransactionDao
    abstract fun cashiersCheckDao(): CashiersCheckDao
    abstract fun flowOfFundDao(): FlowOfFundDao
    abstract fun goodDao(): GoodDao
    abstract fun goodCategoryDao(): GoodCategoryDao
    abstract fun issuerDao(): IssuerDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun sourceRecipientDao(): SourceRecipientDao
    abstract fun storeDao(): StoreDao
    abstract fun transactionCategoryDao(): TransactionCategoryDao

    private val dbState: StateFlow<Boolean>
        get() = msfDBState

    fun getDBState(): StateFlow<Boolean> {
        return dbState
    }

    companion object {

        private val TAG = "DEBUG: " + javaClass.simpleName

        private val msfDBState = MutableStateFlow(false)

        @Volatile
        private var INSTANCE: FinanceDB? = null
        private const val DATABASE_NAME = "finance_db"

        fun getDataBase(context: Context, scope: CoroutineScope): FinanceDB {
//            Toast.makeText(context, "[FinanceDB]: building DB", Toast.LENGTH_SHORT).show()
            msfDBState.value = context.getDatabasePath(DATABASE_NAME).exists()
            // if the INSTANCE is not null, then return it, if it is, then create the database
            Log.d(TAG, "building DB [${msfDBState.value}]")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FinanceDB::class.java,
                        DATABASE_NAME
                )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        .fallbackToDestructiveMigration()
                        .addCallback(DatabaseCallback(scope, context))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class DatabaseCallback(
                private val scope: CoroutineScope,
                private val context: Context
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        Log.d(TAG, "building DB [DatabaseCallback.onCreate]")
                        populateDatabase(database)
                        msfDBState.value = true
                        Log.d(TAG, "DB created [${msfDBState.value}]")
                    }
                }
            }

            /**
             * Populate the database in a new coroutine.
             */
            suspend fun populateDatabase(database: FinanceDB) {
                database.run {
                    Log.d(TAG, "building DB [populateDatabase]")
                    accountTypeDao().insertAll(
                            listOf(
                                    AccountTypeEntity(
                                            context.getString(R.string.account_type_cash),
                                            true,
                                            false
                                    ),
                                    AccountTypeEntity(
                                            context.getString(R.string.account_type_bank),
                                            false,
                                            false
                                    ),
                                    AccountTypeEntity(
                                            context.getString(R.string.account_type_card),
                                            false,
                                            true
                                    )
                            )
                    )
//                    delay(1000)
                    cardTypeDao().insertAll(
                            listOf(
                                    CardTypeEntity(context.getString(R.string.card_type_american_express)),
                                    CardTypeEntity(context.getString(R.string.card_type_discover)),
                                    CardTypeEntity(context.getString(R.string.card_type_google_wallet)),
                                    CardTypeEntity(context.getString(R.string.card_type_jcb)),
                                    CardTypeEntity(context.getString(R.string.card_type_maestro)),
                                    CardTypeEntity(context.getString(R.string.card_type_mastercard)),
                                    CardTypeEntity(context.getString(R.string.card_type_mir)),
                                    CardTypeEntity(context.getString(R.string.card_type_paypal)),
                                    CardTypeEntity(context.getString(R.string.card_type_unionpay)),
                                    CardTypeEntity(context.getString(R.string.card_type_visa)),
                                    CardTypeEntity(context.getString(R.string.card_type_yandex_money))
                            )
                    )
//                    delay(1000)
                    transactionCategoryDao().insertAll(
                            listOf(
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_start_balance),
                                            true,
                                            -1
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_salary),
                                            true,
                                            -1
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_prepayment),
                                            true,
                                            -1
                                    )
                            )
                    )
//                    delay(1000)
                    transactionCategoryDao().insertAll(
                            listOf(
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_all_for_home),
                                            false,
                                            -1
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_clothes),
                                            false,
                                            -1
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_education),
                                            false,
                                            -1
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_other),
                                            false,
                                            -1
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_supermarket),
                                            false,
                                            -1
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_transport),
                                            false,
                                            -1
                                    )
                            )
                    )
//                    delay(1000)
                    var parent: Long = transactionCategoryDao().insertEntity(
                            TransactionCategoryEntity(
                                    context.getString(R.string.transaction_category_auto),
                                    false,
                                    -1
                            )
                    )
//                    delay(1000)
                    transactionCategoryDao().insertAll(
                            listOf(
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_auto_service),
                                            false,
                                            parent
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_gas_station),
                                            false,
                                            parent
                                    )
                            )
                    )
//                    delay(1000)
                    parent = transactionCategoryDao().insertEntity(
                            TransactionCategoryEntity(
                                    context.getString(R.string.transaction_category_health_beauty),
                                    false,
                                    -1
                            )
                    )
//                    delay(1000)
                    transactionCategoryDao().insertAll(
                            listOf(
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_pharmacy),
                                            false,
                                            parent
                                    )
                            )
                    )
//                    delay(1000)
                    parent = transactionCategoryDao().insertEntity(
                            TransactionCategoryEntity(
                                    context.getString(R.string.transaction_category_rest_entertainment),
                                    false,
                                    -1
                            )
                    )
//                    delay(1000)
                    transactionCategoryDao().insertAll(
                            listOf(
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_cinema),
                                            false,
                                            parent
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_restaurant_cafe),
                                            false,
                                            parent
                                    )
                            )
                    )
//                    delay(1000)
                    parent = transactionCategoryDao().insertEntity(
                            TransactionCategoryEntity(
                                    context.getString(R.string.transaction_category_paymnets),
                                    false,
                                    -1
                            )
                    )
//                    delay(1000)
                    transactionCategoryDao().insertAll(
                            listOf(
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_mobile_banking),
                                            false,
                                            parent
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_commission),
                                            false,
                                            parent
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_repayment_on_credit),
                                            false,
                                            parent
                                    )
                            )
                    )
//                    delay(1000)
                    var parentChild: Long = transactionCategoryDao().insertEntity(
                            TransactionCategoryEntity(
                                    context.getString(R.string.transaction_category_public_services),
                                    false,
                                    parent
                            )
                    )
//                    delay(1000)
                    transactionCategoryDao().insertAll(
                            listOf(
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_electric_power),
                                            false,
                                            parentChild
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_home_phone),
                                            false,
                                            parentChild
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_rent),
                                            false,
                                            parentChild
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_heating),
                                            false,
                                            parentChild
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_intercome),
                                            false,
                                            parentChild
                                    )
                            )
                    )
//                    delay(1000)
                    parentChild = transactionCategoryDao().insertEntity(
                            TransactionCategoryEntity(
                                    context.getString(R.string.transaction_category_communication_internet),
                                    false,
                                    parent
                            )
                    )
//                    delay(1000)
                    transactionCategoryDao().insertAll(
                            listOf(
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_mobile),
                                            false,
                                            parentChild
                                    ),
                                    TransactionCategoryEntity(
                                            context.getString(R.string.transaction_category_wired_internet),
                                            false,
                                            parentChild
                                    )
                            )
                    )

                }
            }
        }
    }
}