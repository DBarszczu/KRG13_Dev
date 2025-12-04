package com.rg.krg13_dev.pinappall

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import com.pinappall.integration.ImplementationApi
import com.pinappall.integration.ImplementationClient
import com.pinappall.integration.ServiceConnectionStateListener
import com.pinappall.integration.Result
import com.pinappall.integration.InstallationStatus
import com.pinappall.integration.ConfigurationStatus
import com.pinappall.integration.ConfigurationDeviceStatus
import com.pinappall.integration.TransactionStartInfo
import com.pinappall.integration.TransactionRecord

class PinAppAllClient(
    private val context: Context,
    private val listener: ServiceConnectionStateListener? = null
) {

    private var client: ImplementationClient? = null

    suspend fun connect(): Boolean = withContext(Dispatchers.IO) {
        val r = ImplementationApi.create(context, listener)
        if (r.success) {
            client = r.data!!
            true
        } else false
    }

    suspend fun checkInstallation(): Result<InstallationStatus>? =
        withContext(Dispatchers.IO) { client?.installationStatus() }

    suspend fun checkConfig(): Result<ConfigurationStatus>? =
        withContext(Dispatchers.IO) { client?.configurationStatus() }

    suspend fun configureDevice(key: String): Result<ConfigurationDeviceStatus>? =
        withContext(Dispatchers.IO) { client?.configDevice(key) }

    suspend fun fetchLast(n: Int): Result<Array<TransactionRecord>>? =
        withContext(Dispatchers.IO) { client?.fetchLast(n) }

    fun getTransactionLauncher() =
        ImplementationApi.createTransactionResultContract()

    fun runTransactionWithoutCard(startInfo: TransactionStartInfo) =
        client?.runTransactionWithoutCard(startInfo)

    fun close() {
        client?.close()
    }
}