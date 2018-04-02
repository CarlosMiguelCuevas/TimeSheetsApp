package rraya.nearsoft.com.timesheetsapp.confirmation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_confirmation.*
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.common.extensions.loadImage
import rraya.nearsoft.com.timesheetsapp.common.extensions.preLoadImage
import javax.inject.Inject

class ConfirmationView : Fragment(), ConfirmationViewPresenterContract.View {

    @Inject
    lateinit var presenter: ConfirmationViewPresenterContract.Presenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_confirmation, container, false)
        presenter.setView(this)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.loadGif()
    }

    override fun preloadGif(url: String) {
        gifView.preLoadImage(url, activity)
    }

    override fun showGif(url: String) {
        gifView.loadImage(url, activity)
    }

    override fun showCloseButton() {
        //    TODO: implenet a close button
    }

    override fun hideCloseButton() {
        //    TODO: implenet a close button
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showViewAccordingToGif(isOnTime: Boolean) {
        if (isOnTime) {
            thank.text = getText(R.string.thank_you)
            doing_great.text = getText(R.string.doing_great)

        } else {
            thank.text = getText(R.string.thank_you_but)
            doing_great.text = getText(R.string.next_time)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.unSubscribe()
        presenter.dropView()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context)
    }

}
