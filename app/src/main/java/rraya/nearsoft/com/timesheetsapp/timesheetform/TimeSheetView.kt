package rraya.nearsoft.com.timesheetsapp.timesheetform

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import java.util.*

class TimeSheetView : Fragment(), ViewPresenterContract.View {

    private var mListener: OnSelectedDayFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_time_sheet, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.days_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DaysRecyclerViewAdapter(listOf(Day(Date(), 8), Day(Date(), 8), Day(Date(), 8), Day(Date(), 8)), null)
        return view
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

//
//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (context is OnSelectedDayFragmentInteractionListener) {
//            mListener = context
//        } else {
//            throw RuntimeException(context!!.toString() + " must implement OnSelectedDayFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        mListener = null
//    }

    interface OnSelectedDayFragmentInteractionListener {
        fun onListFragmentInteraction(item: Day)
    }
}
