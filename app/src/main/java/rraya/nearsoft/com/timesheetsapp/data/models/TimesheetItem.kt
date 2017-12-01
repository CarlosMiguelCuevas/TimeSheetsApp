package rraya.nearsoft.com.timesheetsapp.data.models

data class TimesheetItem(val date: String = "",
                         val other: String = "",
                         val otherHours: Int = 0,
                         val totalHours: Int = 0,
                         val hasHoliday: Boolean = false,
                         val workHours: List<Int>?,
                         val ptoHours: Int = 0,
                         val stringDate: String = "",
                         val hasVacation: Boolean = false,
                         val showOption: Boolean = false,
                         val hasOption: Boolean = false,
                         val hasPto: Boolean = false,
                         val hasOther: Boolean = false,
                         val weekDay: String = "",
                         val vacationHours: Int = 0)