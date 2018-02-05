package rraya.nearsoft.com.timesheetsapp.timesheetform.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.day_item.view.*
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.common.extensions.dayMonthFormat
import rraya.nearsoft.com.timesheetsapp.common.extensions.dayOfTheWeekFormat
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import rraya.nearsoft.com.timesheetsapp.timesheetform.TimeSheetView

class DaysRecyclerViewAdapter(private var mValues: List<Day>, private val mListener: TimeSheetView.OnSelectedDayFragmentInteractionListener?) : RecyclerView.Adapter<DaysRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.day_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(mValues[position], mListener)
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

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val dayOfWeekText: TextView = itemView.day_of_week
        val dateText: TextView = itemView.date
        val hoursText: TextView = itemView.hours

        override fun toString(): String {
            return super.toString() + " '" + dateText.text + "'"
        }

        fun bindViews(day: Day, mListener: TimeSheetView.OnSelectedDayFragmentInteractionListener?) {
            dayOfWeekText.text = day.date.dayOfTheWeekFormat()
            dateText.text = day.date.dayMonthFormat()
            hoursText.text = day.totalHours.toString()
            itemView.setOnClickListener {
                mListener?.onListFragmentInteraction(day)
            }
        }
    }
}
