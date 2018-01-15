package rraya.nearsoft.com.timesheetsapp.data.models

data class TimeSheet(val dayList: List<Day>?, val clientList: List<Client>?, val userId: String = "") {

    companion object {
        val noNameFound = "No Name Found"
    }

    constructor(dayList: List<Day>?, clientName: String) : this(dayList, listOf(Client(clientName)))

    fun getClientName(): String {
        return clientList?.get(0)?.name ?: noNameFound
    }

}