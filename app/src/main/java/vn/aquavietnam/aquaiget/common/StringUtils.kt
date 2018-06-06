package vn.aquavietnam.aquaiget

import android.os.Build
import android.text.Html
import android.text.Spanned
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.AquaApp
import java.text.DecimalFormat
import java.text.SimpleDateFormat

/**
 * Created by pqanh on 5/1/2018.
 */
class StringUtils {
    companion object {
        @JvmStatic
        fun convertCurrency(c: Long): String {
            return if (c > 1000) {
                val l: String
                val formatter = DecimalFormat("#,###")
                l = formatter.format(c)
                l + " đ"
            } else {
                c.toString() + " đ"
            }
        }


        @JvmStatic
        fun convertDate(date: String?): String {
            var result = ""
            if(date != null) {
                try {
                    var spf : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
                    val newDate = spf.parse(date)
                    spf = SimpleDateFormat("hh:mm")
                    result  = spf.format(newDate)
                }
                catch (ex:Exception){
                    print(ex)
                }
                return result.toString()
            }else {
                return result.toString()
            }
        }
    }
}