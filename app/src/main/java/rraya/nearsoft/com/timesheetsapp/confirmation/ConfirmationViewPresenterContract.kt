package rraya.nearsoft.com.timesheetsapp.confirmation

import rraya.nearsoft.com.timesheetsapp.common.BaseViewPresenterContract

interface ConfirmationViewPresenterContract {

    interface View : BaseViewPresenterContract.View {

        fun showGif(url: String)

        fun showCloseButton()

        fun hideCloseButton()

        fun showViewAccordingToGif(isOnTime: Boolean)

        fun preloadGif(url: String)

    }

    interface Presenter : BaseViewPresenterContract.Presenter {

        fun closeApp()

        fun loadGif()

        fun setView(view: ConfirmationViewPresenterContract.View)

        fun dropView()

    }

}