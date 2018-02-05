package rraya.nearsoft.com.timesheetsapp.common.extensions

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

fun Calendar.calculateWeekStart(): Date {
    val calendar = this
    val weekDay = calendar.get(Calendar.DAY_OF_WEEK)

    if (weekDay <= Calendar.MONDAY) {
        calendar.add(Calendar.DAY_OF_MONTH, -7)
    }

    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
    return calendar.time
}

fun Calendar.setDefaultTimeReminder() {
    val calendar = this
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
    calendar.set(Calendar.HOUR_OF_DAY, 12)
}