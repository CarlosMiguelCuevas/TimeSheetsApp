package rraya.nearsoft.com.timesheetsapp.data.models

data class TimeSheetPost(val timesheet: List<TimesheetItem>?,
                         val clients: List<ClientsItem>?,
                         val userId: String = "")