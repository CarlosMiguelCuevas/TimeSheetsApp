package rraya.nearsoft.com.timesheetsapp.models

data class TimeSheetPOST(val timesheet: List<TimesheetItem>?,
                         val clients: List<ClientsItem>?,
                         val userId: String = "")