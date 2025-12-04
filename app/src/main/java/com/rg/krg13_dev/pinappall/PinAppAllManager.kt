package com.rg.krg13_dev.pinappall

import android.app.Application
import com.pinappall.integration.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class PinAppAllManager(
    private val app: Application
) {

    private val _state = MutableStateFlow(
        PinAppAllState(
            connected = false,
            installationStatus = null,
            configurationStatus = null,
            configurationResponse = null
        )
    )
    val state = _state.asStateFlow()

    private lateinit var client: PinAppAllClient

    suspend fun init(listener: ServiceConnectionStateListener? = null) {
        client = PinAppAllClient(app, listener)
        val ok = client.connect()
        _state.update { it.copy(connected = ok) }
        if (ok) refreshStatus()
    }

    suspend fun refreshStatus() {
        val installation = client.checkInstallation()
        val config = client.checkConfig()

        _state.update {
            it.copy(
                installationStatus = installation,
                configurationStatus = config
            )
        }
    }

    suspend fun configure(key: String) {
        val res = client.configureDevice(key)
        _state.update { it.copy(configurationResponse = res) }
    }

    suspend fun fetchHistory(limit: Int) =
        withContext(Dispatchers.IO) { client.fetchLast(limit) }

    fun getLauncherContract() =
        ImplementationApi.createTransactionResultContract()

    fun runReversal(info: TransactionStartInfo) =
        client.runTransactionWithoutCard(info)

    fun close() = client.close()
}
