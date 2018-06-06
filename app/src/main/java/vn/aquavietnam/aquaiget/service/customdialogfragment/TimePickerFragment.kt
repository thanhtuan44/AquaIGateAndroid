package vn.aquavietnam.aquaiget.service.customdialogfragment

import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ThanhTuan on 5/16/2018.
 */
class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private lateinit var calendar: Calendar
    var selectTime: ((String) -> Unit)? =  null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute,
                true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR,hourOfDay)
        cal.set(Calendar.MINUTE,minute)
        selectTime?.invoke(SimpleDateFormat("HH:mm").format(cal.time))
    }

}