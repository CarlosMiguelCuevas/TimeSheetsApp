package rraya.nearsoft.com.timesheetsapp.confirmation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_confirmation.*
import pl.droidsonroids.gif.GifDrawable
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.TimeSheetsApp
import rraya.nearsoft.com.timesheetsapp.data.IGifsRepository
import javax.inject.Inject


class ConfirmationActivity : AppCompatActivity() {

    private var userDidGood: Boolean = false
    @Inject lateinit var gifRepo: IGifsRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        (application as TimeSheetsApp).getAppComponent()?.inject(this)

        userDidGood = intent.getBooleanExtra("DID_GOOD", false)

        if(userDidGood){
            gifRepo.wellDoneGif.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                val gifDrawable = GifDrawable(it)
                gifView.setImageDrawable(gifDrawable)
            }, {
                //TODO: sorry not sorry, add the image or something default
            })
        }else{
            gifRepo.notGoodGif.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                val gifDrawable = GifDrawable(it)
                gifView.setImageDrawable(gifDrawable)
            }, {
                //TODO: sorry not sorry, add the image or something default
            })
        }

    }
}
