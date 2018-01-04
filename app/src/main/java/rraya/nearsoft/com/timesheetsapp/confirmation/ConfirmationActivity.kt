package rraya.nearsoft.com.timesheetsapp.confirmation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import dagger.android.DaggerActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_confirmation.*
import pl.droidsonroids.gif.GifDrawable
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.data.IGifsRepository
import javax.inject.Inject


class ConfirmationActivity : DaggerActivity() {

    private var submittedOnTime: Boolean = false
    @Inject lateinit var gifRepo: IGifsRepository
    private var subscriptions = CompositeDisposable()


    companion object {
        const val TAG = "ConfirmationActivity"
        const val DID_SUBMIT_ON_TIME = "ConfirmationActivity"
    }


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        submittedOnTime = savedInstanceState?.getBoolean(DID_SUBMIT_ON_TIME) ?: intent.getBooleanExtra(DID_SUBMIT_ON_TIME, false)
        loadGif()
    }

    private fun loadGif() {
        val subscription: Disposable = defineTypeOfGif(submittedOnTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val gifDrawable = GifDrawable(it)
                    gifView.setImageDrawable(gifDrawable)
                }, {
                    Log.e(TAG, "Error loading gif", it)
                    //TODO: sorry not sorry, add the image or something default
                })

        subscribe(subscription)
    }

    private fun defineTypeOfGif(submittedOnTime: Boolean): Single<ByteArray> {
        if (submittedOnTime) {
            thank.text = getText(R.string.thank_you)
            doing_great.text = getText(R.string.doing_great)
            return gifRepo.getWellDoneGif()

        } else {
            thank.text = getText(R.string.thank_you_but)
            doing_great.text = getText(R.string.next_time)
            return gifRepo.getNotGoodGif()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(DID_SUBMIT_ON_TIME, submittedOnTime)
    }

    override fun onStop() {
        super.onStop()
        unSubscribe()
    }

    private fun subscribe(subscription: Disposable) {
        subscriptions.add(subscription)
    }

    private fun unSubscribe() {
        subscriptions.clear()
    }
}
