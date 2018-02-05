package rraya.nearsoft.com.timesheetsapp.data.models

data class TimeSheet(val dayList: List<Day> = mutableListOf(), val clientList: List<Client> = listOf(), val userId: String = "") {

    companion object {
        const val noNameFound = "No Name Found"
    }

    fun getClientName(): String {
        return if (clientList.isEmpty()) noNameFound else clientList.first().name
    }

}