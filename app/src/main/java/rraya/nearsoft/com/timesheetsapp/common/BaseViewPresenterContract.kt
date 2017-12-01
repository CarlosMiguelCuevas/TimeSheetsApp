package rraya.nearsoft.com.timesheetsapp.common

import io.reactivex.disposables.Disposable

/**
 * Created by ccuevas on 12/1/17.
 */
interface BaseViewPresenterContract {

    interface View {

        //TODO:add more base View contract method if needed
        fun showProgressBar()

        fun hideProgressBar()

    }

    interface Presenter {

        //TODO:add more base Presenter contract method if needed
        fun subscribe(subscription: Disposable)

        fun unSubscribe()

    }

}