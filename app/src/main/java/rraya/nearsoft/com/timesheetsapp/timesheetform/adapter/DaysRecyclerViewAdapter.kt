package rraya.nearsoft.com.timesheetsapp.timesheetform.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.day_item.view.*
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.common.extencions.dayMonthFormat
import rraya.nearsoft.com.timesheetsapp.common.extencions.dayOfTheWeekFormat
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetView

class DaysRecyclerViewAdapter(private var mValues: List<Day>, private val mListener: TimeSheetView.OnSelectedDayFragmentInteractionListener?) : RecyclerView.Adapter<DaysRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.day_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.dayOfWeekText.text = mValues[position].date.dayOfTheWeekFormat()
        holder.dateText.text = mValues[position].date.dayMonthFormat()
        holder.hoursText.text = mValues[position].hours.toString()
        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun setDays(days: List<Day>?) {
        days?.let {
            mValues = days
            notifyDataSetChanged()
        }
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val dayOfWeekText: TextView = mView.day_of_week
        val dateText: TextView = mView.date
        val hoursText: TextView = mView.hours
        var mItem: Day? = null

        override fun toString(): String {
            return super.toString() + " '" + dateText.text + "'"
        }
    }
}
