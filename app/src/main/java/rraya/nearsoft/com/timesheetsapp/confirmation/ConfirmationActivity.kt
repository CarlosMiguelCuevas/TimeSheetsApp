package rraya.nearsoft.com.timesheetsapp.confirmation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_confirmation.*
import pl.droidsonroids.gif.GifDrawable
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.TimeSheetsApp
import rraya.nearsoft.com.timesheetsapp.data.IGifsRepository
import javax.inject.Inject


class ConfirmationActivity : AppCompatActivity() {

    private var submittedOnTime: Boolean = false
    @Inject lateinit var gifRepo: IGifsRepository

    companion object {
        const val TAG = "ConfirmationActivity"
        const val DID_SUBMIT_ON_TIME = "ConfirmationActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        (application as TimeSheetsApp).getAppComponent()?.inject(this)

        submittedOnTime = savedInstanceState?.getBoolean(DID_SUBMIT_ON_TIME) ?: intent.getBooleanExtra(DID_SUBMIT_ON_TIME, false)

        if(submittedOnTime){
            thank.text = getText(R.string.thank_you)
            doing_great.text = getText(R.string.doing_great)
            gifRepo.wellDoneGif.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                val gifDrawable = GifDrawable(it)
                gifView.setImageDrawable(gifDrawable)
            }, {
                Log.e(TAG, "Error loading gif", it)
                //TODO: sorry not sorry, add the image or something default
            })
        }else{
            thank.text = getText(R.string.thank_you_but)
            doing_great.text = getText(R.string.next_time)
            gifRepo.notGoodGif.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                val gifDrawable = GifDrawable(it)
                gifView.setImageDrawable(gifDrawable)
            }, {
                Log.e(TAG, "Error loading gif", it)
                //TODO: sorry not sorry, add the image or something default
            })
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(DID_SUBMIT_ON_TIME, submittedOnTime)
    }
}
