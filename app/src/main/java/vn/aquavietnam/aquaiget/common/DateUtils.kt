package vn.aquavietnam.aquaiget

import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.AquaApp
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by ThanhTuan on 3/27/2018.
 */
class DateUtils {
    companion object {
        val ORDER_DATE_FORMAT_1 = "HH:mm:ss"
        val ORDER_DATE_FORMAT_2 = "HH:mm:ss"
        val ORDER_DATE_FORMAT = "dd/MM/yyyy"
        fun parseProductDateTime(dateStr: Calendar): String {
            val date: String
            val dateFormat = SimpleDateFormat(ORDER_DATE_FORMAT_1)
            date = dateFormat.format(dateStr.time)
            return date
        }

        @JvmStatic
        fun parseOrderDateTime(dateStr: Long): String {
            var dateFormat = SimpleDateFormat(ORDER_DATE_FORMAT_1)
            return dateFormat.format(dateStr)
        }


        @JvmStatic
        fun getCurrentDate(): String {
            val dateFormat = SimpleDateFormat(ORDER_DATE_FORMAT).format(Calendar.getInstance().time)
            return dateFormat
        }

        @JvmStatic
        fun getEndOfDay(cal: Calendar): Calendar {
            cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY))
            cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE))
            cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND))
            cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND))
            return cal
        }

    }
}