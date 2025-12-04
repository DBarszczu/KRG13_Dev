package com.rg.krg13_dev.pinappall

import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import com.pinappall.integration.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaymentViewModel : ViewModel() {

    private val _lastRecord = MutableStateFlow<TransactionRecord?>(null)
    val lastRecord: StateFlow<TransactionRecord?> = _lastRecord.asStateFlow()

    /**
     * 🔵 Start nowej transakcji - zakup biletu (SALE)
     */
    fun startPayment(
        launcher: ActivityResultLauncher<TransactionStartInfo>,
        amountInCents: Int
    ) {
        val info = TransactionStartInfo(
            amount = amountInCents,
            currencyId = 985,            // PLN
            type = TransactionType.SALE, // sprzedaż
            flags = TransactionFlags.NONE,
            transactionId = null,
            integrationKey = null,
            localeCode = null,
            transactionReference = null
        )

        launcher.launch(info)
    }

    /**
     * 🟠 Start transakcji sprawdzania biletu (TOKENIZATION)
     */
    fun startTicketCheck(
        launcher: ActivityResultLauncher<TransactionStartInfo>
    ) {
        val info = TransactionStartInfo(
            amount = 0,                       // ignorowane, ale wymagane
            currencyId = 985,
            type = TransactionType.TOKENIZATION,
            flags = TransactionFlags.NONE,
            transactionId = null,
            integrationKey = null,
            localeCode = null,
            transactionReference = null
        )

        launcher.launch(info)
    }

    /**
     * 🔥 Odbiór rekordu transakcji z terminala
     */
    fun onResult(record: TransactionRecord?) {

        if (record != null) {
            Log.d("PAYMENT", "===== TRANSACTION RESULT =====")
            Log.d("PAYMENT", "ID: ${record.id}")
            Log.d("PAYMENT", "TYPE: ${record.type}")
            Log.d("PAYMENT", "DATE: ${record.date}")
            Log.d("PAYMENT", "CURRENCY ID: ${record.currencyId}")
            Log.d("PAYMENT", "CURRENCY NAME: ${record.currencyName}")
            Log.d("PAYMENT", "AMOUNT: ${record.amount}")
            Log.d("PAYMENT", "STATUS: ${record.status}")
            Log.d("PAYMENT", "STATUS TEXT: ${record.statusText}")
            Log.d("PAYMENT", "CARD TYPE: ${record.cardType}")
            Log.d("PAYMENT", "MASKED CARD: ${record.maskCardNumber}")
            Log.d("PAYMENT", "CONFIRMATION URL: ${record.confirmationUrl}")
            Log.d("PAYMENT", "EXTRA PARAMS: ${record.extraParams}")
            Log.d("PAYMENT", "================================")
        }

        _lastRecord.value = record
    }

    fun clear() {
        _lastRecord.value = null
    }
}
