package rraya.nearsoft.com.timesheetsapp.confirmation

import rraya.nearsoft.com.timesheetsapp.common.BaseViewPresenterContract

//TODO: modify this contract as needed
interface ViewPresenterContract {

    interface View : BaseViewPresenterContract.View {

        fun onErrorloadingGif(error: Throwable)

    }

    interface Presenter : BaseViewPresenterContract.Presenter {

        fun loadGif()

    }

}