package rraya.nearsoft.com.timesheetsapp.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxBasePresenter : BaseViewPresenterContract.Presenter {

    private var subscriptions = CompositeDisposable()

    override fun subscribe(subscription: Disposable) {
        subscriptions.add(subscription)
    }

    override fun unSubscribe() {
        subscriptions.clear()
    }
}