package rraya.nearsoft.com.timesheetsapp.timesheetform

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_time_sheet.*
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import javax.inject.Inject
import kotlin.collections.ArrayList
import android.content.Intent
import android.net.Uri
import dagger.android.support.DaggerFragment
import rraya.nearsoft.com.timesheetsapp.TimeSheetsApp
import java.text.SimpleDateFormat
import java.util.*


class TimeSheetView : DaggerFragment(), TimesheetsPresenterContract.View {

    private var mListener: OnSelectedDayFragmentInteractionListener? = null
    @Inject lateinit var presenter: TimesheetsPresenterContract.Presenter

    private lateinit var adapter: DaysRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_time_sheet, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.days_list)
        adapter = DaysRecyclerViewAdapter(ArrayList(), null)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        presenter.loadTimeSheet()

        val editAction = View.OnClickListener{
            showLeavingAppDialog()
        }

        editHoursBtn.setOnClickListener(editAction)
        edit_bottom_btn.setOnClickListener(editAction)
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

    override fun showSendTimesheetButton() {
        send_timesheet_container.visibility = View.VISIBLE
    }

    override fun hideSendTimesheetButton() {
        send_timesheet_container.visibility = View.GONE
    }

    override fun showDaysOfWeek(days: List<Day>?) {
        adapter.setDays(days)
        showWeekRange(days)
    }

    private fun showWeekRange(days: List<Day>?) {
        if(days != null && days.isNotEmpty()) {
            val sdf = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())
            val firstDay = sdf.format(days.first().date)
            val lastDay = sdf.format(days.last().date)
            val weekRange = "$firstDay - $lastDay"
            week_days_range.text = weekRange
        }
    }

    private fun showLeavingAppDialog(){
        val builder = AlertDialog.Builder(context, R.style.TimesheetsDialog)
        builder.setTitle(getText(R.string.edit_timesheet_in_browser))
                .setMessage(getText(R.string.cant_edit_yet))
                .setPositiveButton(getText(R.string.ok_edit_browser).toString()) { _, _ -> sendUserToEditInWeb() }
                .setNegativeButton(getText(R.string.cancel)) { dialog, _ -> dialog.cancel() }
        builder.create().show()
    }

    private fun sendUserToEditInWeb() {
        val url = presenter.getUrlForTimesheetEditing()
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}
