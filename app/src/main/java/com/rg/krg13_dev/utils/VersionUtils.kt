package com.rg.krg13_dev.utils

import android.content.Context
import android.content.pm.PackageManager

object VersionUtils {

    fun getAppVersion(context: Context): String {
        return try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            pInfo.versionName ?: "Unknown"
        } catch (e: PackageManager.NameNotFoundException) {
            "Unknown"
        }
    }
}
