package com.rg.krg13_dev.autocomputer

class AutoComputerStatusManager {

    private var DEVICE_LOCKED_FLAG = false
    private var REQUEST_FOR_LOCK_FLAG = false
    private var REQUEST_FOR_UNLOCK_FLAG = false
    private var REQUEST_FOR_REPORT_READING_FLAG = true
    private var REQUEST_FOR_CARD_NUMBER_READING_FLAG = false
    private var REQUEST_FOR_INFORMATION_ON_CARRIAGE_FLAG = false
    private var MISSING_RAIL_COURSE_PARAMETERS_FLAG = false
    private var MISSING_COLOR_LIST_FLAG = false
    private var MISSING_STOP_LIST_FLAG = true
    private var MISSING_BLACKLIST_FLAG = true
    private var MISSING_TARIFF_TABLE_FLAG = true
    private var MISSING_COURSE_PARAMETERS_FLAG = true
    private var DAMAGED_MIFARE_SYSTEM_OR_SELF_FLAG = false
    private var DAMAGED_PRINTER_FLAG = false
    private var BLOCKED_SLOT_FLAG = false
    private var MISSING_DATA_FOR_PRINTING_FLAG = false
    private var MISSING_CURRENT_TIME_DATA_FLAG = true

    var onFlagChanged: ((flagName: String, newValue: Boolean) -> Unit)? = null

    fun setFlag(flag: String, value: Boolean) {
        when (flag) {
            "DEVICE_LOCKED_FLAG" -> DEVICE_LOCKED_FLAG = value
            "REQUEST_FOR_LOCK_FLAG" -> REQUEST_FOR_LOCK_FLAG = value
            "REQUEST_FOR_UNLOCK_FLAG" -> REQUEST_FOR_UNLOCK_FLAG = value
            "REQUEST_FOR_REPORT_READING_FLAG" -> REQUEST_FOR_REPORT_READING_FLAG = value
            "REQUEST_FOR_CARD_NUMBER_READING_FLAG" -> REQUEST_FOR_CARD_NUMBER_READING_FLAG = value
            "REQUEST_FOR_INFORMATION_ON_CARRIAGE_FLAG" -> REQUEST_FOR_INFORMATION_ON_CARRIAGE_FLAG = value
            "MISSING_RAIL_COURSE_PARAMETERS_FLAG" -> MISSING_RAIL_COURSE_PARAMETERS_FLAG = value
            "MISSING_COLOR_LIST_FLAG" -> MISSING_COLOR_LIST_FLAG = value
            "MISSING_STOP_LIST_FLAG" -> MISSING_STOP_LIST_FLAG = value
            "MISSING_BLACKLIST_FLAG" -> MISSING_BLACKLIST_FLAG = value
            "MISSING_TARIFF_TABLE_FLAG" -> MISSING_TARIFF_TABLE_FLAG = value
            "MISSING_COURSE_PARAMETERS_FLAG" -> MISSING_COURSE_PARAMETERS_FLAG = value
            "DAMAGED_MIFARE_SYSTEM_OR_SELF_FLAG" -> DAMAGED_MIFARE_SYSTEM_OR_SELF_FLAG = value
            "DAMAGED_PRINTER_FLAG" -> DAMAGED_PRINTER_FLAG = value
            "BLOCKED_SLOT_FLAG" -> BLOCKED_SLOT_FLAG = value
            "MISSING_DATA_FOR_PRINTING_FLAG" -> MISSING_DATA_FOR_PRINTING_FLAG = value
            "MISSING_CURRENT_TIME_DATA_FLAG" -> MISSING_CURRENT_TIME_DATA_FLAG = value
            else -> return
        }
        onFlagChanged?.invoke(flag, value)
        updateNoCommunicationLock()

    }

    fun updateStatusFlags(status: AutoComputerStatus) {
        status.updateStatusFlag(StatusFlag.DEVICE_LOCKED, DEVICE_LOCKED_FLAG)
        status.updateStatusFlag(StatusFlag.REQUEST_FOR_LOCK, REQUEST_FOR_LOCK_FLAG)
        status.updateStatusFlag(StatusFlag.REQUEST_FOR_UNLOCK, REQUEST_FOR_UNLOCK_FLAG)
        status.updateStatusFlag(StatusFlag.REQUEST_FOR_REPORT_READING, REQUEST_FOR_REPORT_READING_FLAG)
        status.updateStatusFlag(StatusFlag.REQUEST_FOR_CARD_NUMBER_READING, REQUEST_FOR_CARD_NUMBER_READING_FLAG)
        status.updateStatusFlag(StatusFlag.REQUEST_FOR_INFORMATION_ON_CARRIAGE, REQUEST_FOR_INFORMATION_ON_CARRIAGE_FLAG)
        status.updateStatusFlag(StatusFlag.MISSING_RAIL_COURSE_PARAMETERS, MISSING_RAIL_COURSE_PARAMETERS_FLAG)
        status.updateStatusFlag(StatusFlag.MISSING_COLOR_LIST, MISSING_COLOR_LIST_FLAG)
        status.updateStatusFlag(StatusFlag.MISSING_STOP_LIST, MISSING_STOP_LIST_FLAG)
        status.updateStatusFlag(StatusFlag.MISSING_BLACKLIST, MISSING_BLACKLIST_FLAG)
        status.updateStatusFlag(StatusFlag.MISSING_TARIFF_TABLE, MISSING_TARIFF_TABLE_FLAG)
        status.updateStatusFlag(StatusFlag.MISSING_COURSE_PARAMETERS, MISSING_COURSE_PARAMETERS_FLAG)
        status.updateStatusFlag(StatusFlag.DAMAGED_MIFARE_SYSTEM_OR_SELF, DAMAGED_MIFARE_SYSTEM_OR_SELF_FLAG)
        status.updateStatusFlag(StatusFlag.DAMAGED_PRINTER, DAMAGED_PRINTER_FLAG)
        status.updateStatusFlag(StatusFlag.BLOCKED_SLOT, BLOCKED_SLOT_FLAG)
        status.updateStatusFlag(StatusFlag.MISSING_DATA_FOR_PRINTING, MISSING_DATA_FOR_PRINTING_FLAG)
        status.updateStatusFlag(StatusFlag.MISSING_CURRENT_TIME_DATA, MISSING_CURRENT_TIME_DATA_FLAG)

        mapOf(
            "DEVICE_LOCKED_FLAG" to DEVICE_LOCKED_FLAG,
            "REQUEST_FOR_LOCK_FLAG" to REQUEST_FOR_LOCK_FLAG,
            "REQUEST_FOR_UNLOCK_FLAG" to REQUEST_FOR_UNLOCK_FLAG,
            "REQUEST_FOR_REPORT_READING_FLAG" to REQUEST_FOR_REPORT_READING_FLAG,
            "REQUEST_FOR_CARD_NUMBER_READING_FLAG" to REQUEST_FOR_CARD_NUMBER_READING_FLAG,
            "REQUEST_FOR_INFORMATION_ON_CARRIAGE_FLAG" to REQUEST_FOR_INFORMATION_ON_CARRIAGE_FLAG,
            "MISSING_RAIL_COURSE_PARAMETERS_FLAG" to MISSING_RAIL_COURSE_PARAMETERS_FLAG,
            "MISSING_COLOR_LIST_FLAG" to MISSING_COLOR_LIST_FLAG,
            "MISSING_STOP_LIST_FLAG" to MISSING_STOP_LIST_FLAG,
            "MISSING_BLACKLIST_FLAG" to MISSING_BLACKLIST_FLAG,
            "MISSING_TARIFF_TABLE_FLAG" to MISSING_TARIFF_TABLE_FLAG,
            "MISSING_COURSE_PARAMETERS_FLAG" to MISSING_COURSE_PARAMETERS_FLAG,
            "DAMAGED_MIFARE_SYSTEM_OR_SELF_FLAG" to DAMAGED_MIFARE_SYSTEM_OR_SELF_FLAG,
            "DAMAGED_PRINTER_FLAG" to DAMAGED_PRINTER_FLAG,
            "BLOCKED_SLOT_FLAG" to BLOCKED_SLOT_FLAG,
            "MISSING_DATA_FOR_PRINTING_FLAG" to MISSING_DATA_FOR_PRINTING_FLAG,
            "MISSING_CURRENT_TIME_DATA_FLAG" to MISSING_CURRENT_TIME_DATA_FLAG
        ).forEach { (name, value) ->
            onFlagChanged?.invoke(name, value)
        }

        updateNoCommunicationLock()

    }

    private fun updateNoCommunicationLock() {

        val requiredFlags = listOf(
            REQUEST_FOR_REPORT_READING_FLAG,
            REQUEST_FOR_CARD_NUMBER_READING_FLAG,
            REQUEST_FOR_INFORMATION_ON_CARRIAGE_FLAG,
            MISSING_RAIL_COURSE_PARAMETERS_FLAG,
            MISSING_COLOR_LIST_FLAG,
            MISSING_STOP_LIST_FLAG,
            MISSING_BLACKLIST_FLAG,
            MISSING_TARIFF_TABLE_FLAG,
            MISSING_COURSE_PARAMETERS_FLAG,
            DAMAGED_MIFARE_SYSTEM_OR_SELF_FLAG,
            DAMAGED_PRINTER_FLAG,
            BLOCKED_SLOT_FLAG,
            MISSING_DATA_FOR_PRINTING_FLAG,
            MISSING_CURRENT_TIME_DATA_FLAG
        )

        DeviceStateHolder.isDeviceLocked_NoCommunication.value =
            requiredFlags.any { it }
    }



}
