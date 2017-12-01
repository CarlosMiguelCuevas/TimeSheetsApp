package rraya.nearsoft.com.timesheetsapp.timesheetform

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rraya.nearsoft.com.timesheetsapp.R

class TimeSheetView : Fragment(), ViewPresenterContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_sheet, container, false)
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onErrorSubmit(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onErrorLoading(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessSubmit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
