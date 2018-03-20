package rraya.nearsoft.com.timesheetsapp.splashloginscreen

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import rraya.nearsoft.com.timesheetsapp.common.RxBasePresenter
import rraya.nearsoft.com.timesheetsapp.confirmation.ConfirmationViewPresenterContract
import rraya.nearsoft.com.timesheetsapp.data.IGifsRepository

class ConfirmationPresenter(private val gifRepository: IGifsRepository) : RxBasePresenter(), ConfirmationViewPresenterContract.Presenter {

    private var mConfirmationView: ConfirmationViewPresenterContract.View? = null

    companion object {
        const val TAG = "ConfirmationActivity"
    }

    override fun setView(view: ConfirmationViewPresenterContract.View) {
        mConfirmationView = view
    }

    override fun closeApp() {
//        TODO: implement when the button is implemented
    }

    override fun loadGif() {
//        TODO:ShowSpinners/Progressbar
        mConfirmationView?.showViewAccordingToGif(gifRepository.isSubmittedOnTime())

        val subscription: Disposable = gifRepository.getSuccessGif()
                .map({ url ->
                    mConfirmationView?.preloadGif(url)
                    return@map url
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({
                    //TODO: add code to hide Spinners/Progressbar
                })
                .subscribe({
                    mConfirmationView?.showGif(it)
                }, {
                    Log.e(TAG, "Error loading gif", it)
                    //TODO: sorry not sorry, add the image or something default
                })

        subscribe(subscription)

    }

    override fun dropView() {
        mConfirmationView = null
    }

}