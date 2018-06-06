package vn.aquavietnam.aquaiget.service.customdialogfragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import vn.aquavietnam.aquaiget.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ThanhTuan on 5/16/2018.
 */
class DatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var calendar: Calendar
    var selectDate: ((String) -> Unit)? =  null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DATE)
        return DatePickerDialog(
                activity,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                this, // DatePickerDialog.OnDateSetListener
                year, // Year
                month, // Month of year
                day // Day of month
        )
    }


    // When date set and press ok button in date picker dialog
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR,year)
        cal.set(Calendar.MONTH,month)
        cal.set(Calendar.DATE,day)
        selectDate?.invoke(SimpleDateFormat("dd/MM/yyyy").format(cal.time))
    }
}