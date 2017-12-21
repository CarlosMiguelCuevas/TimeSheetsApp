package rraya.nearsoft.com.timesheetsapp.timesheetform

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import rraya.nearsoft.com.timesheetsapp.R
import rraya.nearsoft.com.timesheetsapp.data.models.Day
import java.text.SimpleDateFormat
import java.util.*


class DaysRecyclerViewAdapter(private var mValues: List<Day>, private val mListener: TimeSheetView.OnSelectedDayFragmentInteractionListener?) : RecyclerView.Adapter<DaysRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.day_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]

        val dayOfWeekFormat = SimpleDateFormat("E", Locale.getDefault())
        holder.dayOfWeekText.text = dayOfWeekFormat.format(mValues[position].date)

        val dayMonthFormat = SimpleDateFormat("MMM d", Locale.getDefault())
        holder.dateText.text = dayMonthFormat.format(mValues[position].date)

        holder.hoursText.text = "${mValues[position].hours}"

        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val dayOfWeekText: TextView = mView.findViewById(R.id.day_of_week) as TextView
        val dateText: TextView = mView.findViewById(R.id.date) as TextView
        val hoursText: TextView = mView.findViewById(R.id.hours) as TextView
        var mItem: Day? = null

        override fun toString(): String {
            return super.toString() + " '" + dateText.text + "'"
        }
    }

    fun setDays(days: List<Day>) {
        mValues = days
        notifyDataSetChanged()
    }
}
