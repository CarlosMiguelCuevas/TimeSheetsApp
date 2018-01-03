package rraya.nearsoft.com.timesheetsapp.common.extencions

import java.text.SimpleDateFormat
import java.util.*

fun Date.dayOfTheWeekFormat(localeFormat: Locale = Locale.getDefault()): String {
    return SimpleDateFormat("E", localeFormat).format(this)
}

fun Date.dayMonthFormat(localeFormat: Locale = Locale.getDefault()): String {
    return SimpleDateFormat("MMM d", localeFormat).format(this)
}

fun Date.yearMonthDayFormat(localeFormat: Locale = Locale.getDefault()): String {
    return SimpleDateFormat("yyyy-MM-dd", localeFormat).format(this)
}

fun Date.monthDayYearFormat(localeFormat: Locale = Locale.getDefault()): String {
    return SimpleDateFormat("MMM dd yyyy", localeFormat).format(this)
}
