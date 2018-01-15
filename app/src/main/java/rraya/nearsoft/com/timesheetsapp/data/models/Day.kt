package rraya.nearsoft.com.timesheetsapp.data.models

import java.util.*

data class Day(val date: Date,
               val totalHours: Int = 0,
               val other: String = "",
               val otherHours: Int = 0,
               val hasHoliday: Boolean = false,
               val workHours: List<Int>? = null,
               val ptoHours: Int = 0,
               val stringDate: String = "",
               val hasVacation: Boolean = false,
               val showOption: Boolean = false,
               val hasOption: Boolean = false,
               val hasPto: Boolean = false,
               val hasOther: Boolean = false,
               val weekDay: String = "",
               val vacationHours: Int = 0) {
}