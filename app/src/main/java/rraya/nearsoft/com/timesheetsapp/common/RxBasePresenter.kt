package rraya.nearsoft.com.timesheetsapp.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by ccuevas on 12/1/17.
 */
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