package rraya.nearsoft.com.timesheetsapp.models

data class TimesheetItem(val date: String = "",
                         val other: String = "",
                         val otherHours: Int = 0,
                         val totalHours: Int = 0,
                         val hasHoliday: Boolean = false,
                         val workHours: List<Int>?,
                         val PTOHours: Int = 0,
                         val stringDate: String = "",
                         val hasVacation: Boolean = false,
                         val showOption: Boolean = false,
                         val hasOption: Boolean = false,
                         val hasPTO: Boolean = false,
                         val hasOther: Boolean = false,
                         val weekDay: String = "",
                         val vacationHours: Int = 0)