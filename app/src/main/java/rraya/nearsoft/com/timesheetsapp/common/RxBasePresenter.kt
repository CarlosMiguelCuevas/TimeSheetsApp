package rraya.nearsoft.com.timesheetsapp.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxBasePresenter : BaseViewPresenterContract.Presenter {

    protected var subscriptions = CompositeDisposable()

    override fun subscribe(subscription: Disposable) {
        subscriptions.add(subscription)
    }

    override fun unSubscribe() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
        subscriptions.clear()
    }
}