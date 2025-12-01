package com.rg.krg13_dev.autocomputer

class AutoComputerStatus {

    private var value: Int = 0

    fun setStatus(flag: StatusFlag, enabled: Boolean) {
        value = if (enabled) {
            value or (1 shl flag.bitPosition)
        } else {
            value and (1 shl flag.bitPosition).inv()
        }
    }

    fun updateStatusFlag(flag: StatusFlag, enabled: Boolean) = setStatus(flag, enabled)

    fun getStatus(): Int = value

    fun resetStatusFlag(flag: StatusFlag) {
        value = value and (1 shl flag.bitPosition).inv()
    }
}
