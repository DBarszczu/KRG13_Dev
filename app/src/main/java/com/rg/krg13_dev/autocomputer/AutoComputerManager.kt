package com.rg.krg13_dev.autocomputer

import android.util.Log
import kotlinx.coroutines.*
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.SocketException
import java.nio.charset.Charset
import com.rg.krg13_dev.autocomputer.parser.SetJPars
import com.rg.krg13_dev.autocomputer.parser.StopsParser
import java.util.Calendar


class AutoComputerManager(
    private val statusManager: AutoComputerStatusManager,
    private val status: AutoComputerStatus,
    private val data: Data,
    private val port: Int = 1026,
    private val viewModel: AutoComputerViewModel,
    private val charset: Charset = Charset.forName("cp852")
) {

    private var socket: DatagramSocket? = null
    private var job: Job? = null

    fun start(scope: CoroutineScope) {
        if (job != null) return

        socket = DatagramSocket(port).apply { broadcast = true }
        val s = socket!!

        job = scope.launch(Dispatchers.IO) {
            val buffer = ByteArray(10_240)
            var lastRequestTs = System.currentTimeMillis()
            val timeoutMs = 1500L

            // 🔍 WATCHDOG komunikacji
            launch {
                while (isActive) {
                    delay(500)
                    if (System.currentTimeMillis() - lastRequestTs > timeoutMs) {
                        viewModel.onNoCommunication()
                    }
                }
            }

            // 🔊 NASŁUCH UDP
            while (isActive) {
                try {
                    val packet = DatagramPacket(buffer, buffer.size)
                    s.receive(packet)

                    lastRequestTs = System.currentTimeMillis()
                    viewModel.onCommunicationRestored()

                    handlePacket(packet, s)

                } catch (e: SocketException) {
                    Log.w("UDP", "Socket zamknięty — STOP")
                    break
                } catch (e: Exception) {
                    Log.e("UDP", "Błąd UDP", e)
                }
            }

            if (!s.isClosed) s.close()
        }
    }

    fun stop() {
        job?.cancel()
        job = null
        socket?.let { if (!it.isClosed) it.close() }
        socket = null
    }


    // ============================================================
    // OBSŁUGA KOMEND
    // ============================================================

    private fun handlePacket(packet: DatagramPacket, socket: DatagramSocket) {

        val ascii = String(packet.data, 0, packet.length, charset)
        val hex = packet.data.copyOf(packet.length).joinToString(" ") { "%02X".format(it) }
        val code = packet.data[0].toInt() and 0xFF

        when (AcRequest.fromCode(code)) {

            AcRequest.REQ_STATUS -> sendStatus(packet, socket)
            AcRequest.REQ_CURRENT_DATE_TIME -> sendDateTime(packet, socket)

            AcRequest.REQ_PRINTER_CHARACTER -> sendPrinter(packet, socket, ascii)
            AcRequest.REQ_CLEAR_TICKET_COUNTER -> sendSimple(packet, socket, AcAnswer.ANS_CLEAR_TICKET_COUNTER)

            AcRequest.REQ_LOCK_KRG -> sendLock(packet, socket)
            AcRequest.REQ_UNLOCK_KRG -> sendUnlock(packet, socket)

            AcRequest.REQ_READ_SOFTWARE_VERSION -> sendSoftware(packet, socket)

            AcRequest.REQ_SAVE_COURSE_PARAMETER -> sendSaveCourse(packet, socket, ascii)
            AcRequest.REQ_SAVE_STOPS_LIST -> sendSaveStops(packet, socket, hex)

            AcRequest.REQ_READ_REPORT -> sendReport(packet, socket)
            AcRequest.REQ_SAVE_TARIFF_TABLE -> sendTariff(packet, socket, hex)
            AcRequest.REQ_SAVE_BLACK_LIST -> sendBlackList(packet, socket, hex)

            AcRequest.REQ_READ_CARD_NUMBER_INFO -> sendCardInfo(packet, socket)
            AcRequest.REQ_SAVE_RAIL_COURSE_PARAMETER -> sendRailCourse(packet, socket, ascii)

            AcRequest.REQ_RESTART_ME -> sendSimple(packet, socket, AcAnswer.ANS_RESTART_ME)

            else -> Log.d("UDP", "Nieobsługiwana komenda 0x${code.toString(16)}")
        }
    }


    // ============================================================
    // STATUS — pełna poprawna ramka 14 bajtów
    // ============================================================

    private fun sendStatus(packet: DatagramPacket, socket: DatagramSocket) {

        statusManager.updateStatusFlags(status)

        val resp = ByteArray(14)
        resp[0] = AcAnswer.ANS_STATUS.code.toByte()

        val st = status.getStatus()
        resp[1] = (st shr 24).toByte()
        resp[2] = (st shr 16).toByte()
        resp[3] = (st shr 8).toByte()
        resp[4] = st.toByte()

        val ticketCounter: Short = 0
        resp[5] = (ticketCounter.toInt() shr 8).toByte()
        resp[6] = (ticketCounter.toInt() and 0xFF).toByte()

        val fw = "v100jz".toByteArray(Charsets.US_ASCII)
        System.arraycopy(fw, 0, resp, 7, fw.size)

        socket.send(DatagramPacket(resp, resp.size, packet.address, packet.port))

        Log.d("UDP", "ANS_STATUS wysłano → ${resp.joinToString(" ") { "%02X".format(it) }}")
    }


    // ============================================================
    // PROSTE ODPOWIEDZI
    // ============================================================

    private fun sendSimple(packet: DatagramPacket, socket: DatagramSocket, ans: AcAnswer) {
        val a = byteArrayOf(ans.code.toByte())
        socket.send(DatagramPacket(a, a.size, packet.address, packet.port))
    }

    private fun sendDateTime(packet: DatagramPacket, socket: DatagramSocket) {

        val now = java.util.Calendar.getInstance()

        val year = now.get(Calendar.YEAR) - 2000
        val month = now.get(Calendar.MONTH) + 1
        val day = now.get(Calendar.DAY_OF_MONTH)

        val hour = now.get(Calendar.HOUR_OF_DAY)
        val minute = now.get(Calendar.MINUTE)
        val second = now.get(Calendar.SECOND)

        val resp = byteArrayOf(
            AcAnswer.ANS_CURRENT_DATE_TIME.code.toByte(),
            year.toByte(),
            month.toByte(),
            day.toByte(),
            hour.toByte(),
            minute.toByte(),
            second.toByte()
        )

        socket.send(
            DatagramPacket(resp, resp.size, packet.address, packet.port)
        )

        statusManager.setFlag("MISSING_CURRENT_TIME_DATA_FLAG", false)
        statusManager.updateStatusFlags(status)

        Log.d("UDP", "ANS_CURRENT_DATE_TIME → ${resp.joinToString(" ") { "%02X".format(it) }}")
    }


    private fun sendPrinter(packet: DatagramPacket, socket: DatagramSocket, ascii: String) {
        sendSimple(packet, socket, AcAnswer.ANS_PRINTER_CHARACTER)
        statusManager.setFlag("MISSING_DATA_FOR_PRINTING_FLAG", false)
        statusManager.updateStatusFlags(status)
    }


    // ============================================================
    // BLOKADA / ODBLOKOWANIE
    // ============================================================

    private fun sendLock(packet: DatagramPacket, socket: DatagramSocket) {
        sendSimple(packet, socket, AcAnswer.ANS_LOCK_KRG)

        statusManager.setFlag("DEVICE_LOCKED_FLAG", true)
        statusManager.updateStatusFlags(status)

        viewModel.onDeviceLocked()   // 🔒 dla UI
        viewModel.onLock()

        Log.d("UDP", "LOCK_KRG → urządzenie zablokowane")
    }

    private fun sendUnlock(packet: DatagramPacket, socket: DatagramSocket) {
        sendSimple(packet, socket, AcAnswer.ANS_UNLOCK_KRG)

        statusManager.setFlag("DEVICE_LOCKED_FLAG", false)
        statusManager.updateStatusFlags(status)

        viewModel.onDeviceUnlocked() // 🔓 dla UI
        viewModel.onUnlock()

        Log.d("UDP", "UNLOCK_KRG → urządzenie odblokowane")
    }


    // ============================================================
    // OPROGRAMOWANIE
    // ============================================================

    private fun sendSoftware(packet: DatagramPacket, socket: DatagramSocket) {
        val resp = ByteArray(31)
        resp[0] = AcAnswer.ANS_READ_SOFTWARE_VERSION.code.toByte()

        "v100db".toByteArray().copyInto(resp, 1)
        "Jun 3 2025 12:12:12".toByteArray().copyInto(resp, 7)
        "666".toByteArray().copyInto(resp, 26)

        socket.send(DatagramPacket(resp, resp.size, packet.address, packet.port))
    }


    // ============================================================
    // ZAPIS LIST / TARYF / KURSÓW
    // ============================================================

    private fun sendSaveCourse(packet: DatagramPacket, socket: DatagramSocket, ascii: String) {

        try {
            val params = SetJPars().parse(ascii.trim())
            viewModel.onNewCourse(params)
        } catch (e: Exception) {
            Log.e("SETJ", "Błąd parsowania SETJ: ${e.message}")
        }

        statusManager.setFlag("MISSING_COURSE_PARAMETERS_FLAG", false)
        statusManager.updateStatusFlags(status)
        sendSimple(packet, socket, AcAnswer.ANS_SAVE_COURSE_PARAMETER)
    }


    private fun sendSaveStops(packet: DatagramPacket, socket: DatagramSocket, hex: String) {

        try {
            val stops = StopsParser.parse(packet)
            viewModel.onNewStops(stops)
        } catch (e: Exception) {
            Log.e("STOP_PARSER", "Błąd: ${e.message}")
        }

        statusManager.setFlag("MISSING_STOP_LIST_FLAG", false)
        statusManager.updateStatusFlags(status)
        sendSimple(packet, socket, AcAnswer.ANS_SAVE_STOPS_LIST)
    }


    private fun sendTariff(packet: DatagramPacket, socket: DatagramSocket, hex: String) {

        try {
            // 1. Surowe dane z pakietu
            val dataBytes = packet.data.copyOf(packet.length)

            Log.d(
                "TARIFF_RAW",
                "Odebrane bajty (${packet.length}): ${
                    dataBytes.joinToString(", ") { it.toUByte().toString() }
                }"
            )

            // 3. Aktualizacja flag
            statusManager.setFlag("MISSING_TARIFF_TABLE_FLAG", false)
            statusManager.updateStatusFlags(status)

        } catch (e: Exception) {
            Log.e("TARIFF", "Błąd przetwarzania taryfy: ${e.message}", e)
        }

        // 4. Odesłanie odpowiedzi
        sendSimple(packet, socket, AcAnswer.ANS_SAVE_TARIFF_TABLE)
    }





    private fun sendBlackList(packet: DatagramPacket, socket: DatagramSocket, hex: String) {
        statusManager.setFlag("MISSING_BLACKLIST_FLAG", false)
        statusManager.updateStatusFlags(status)
        sendSimple(packet, socket, AcAnswer.ANS_SAVE_BLACK_LIST)
    }


    // ============================================================
    // RAPORT
    // ============================================================

    private fun sendReport(packet: DatagramPacket, socket: DatagramSocket) {
        val raw = data.reportFrame.toByteArray()
        val resp = ByteArray(1 + raw.size)

        resp[0] = AcAnswer.ANS_READ_REPORT.code.toByte()
        raw.copyInto(resp, 1)

        socket.send(DatagramPacket(resp, resp.size, packet.address, packet.port))

        statusManager.setFlag("REQUEST_FOR_REPORT_READING_FLAG", false)
        statusManager.updateStatusFlags(status)
    }


    // ============================================================
    // KARTA
    // ============================================================

    private fun sendCardInfo(packet: DatagramPacket, socket: DatagramSocket) {
        val card = byteArrayOf(1,2,3,4,5,6,7,8)
        val resp = ByteArray(1 + card.size)

        resp[0] = AcAnswer.ANS_READ_CARD_NUMBER_INFO.code.toByte()
        card.copyInto(resp, 1)

        socket.send(DatagramPacket(resp, resp.size, packet.address, packet.port))
    }


    // ============================================================
    // KURS KOLEJOWY
    // ============================================================

    private fun sendRailCourse(packet: DatagramPacket, socket: DatagramSocket, ascii: String) {
        val bytes = ascii.toByteArray()
        val resp = ByteArray(1 + bytes.size)

        resp[0] = AcAnswer.ANS_SAVE_RAIL_COURSE_PARAMETER.code.toByte()
        bytes.copyInto(resp, 1)

        socket.send(DatagramPacket(resp, resp.size, packet.address, packet.port))
    }
}
