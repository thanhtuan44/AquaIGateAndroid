package vn.aquavietnam.aquaiget.common

import android.content.Context
import com.ligl.android.widget.iosdialog.IOSDialog

/**
 * Created by ThanhTuan on 6/4/2018.
 */
object Utilities {
    var alert : IOSDialog.Builder? = null
    fun showAlert(context: Context?,message: String) {
        if(context != null) {
            alert = IOSDialog.Builder(context).setTitle("Thông báo").setMessage(message)
                    .setPositiveButton("OK",{dialog, which ->
                        dialog.dismiss()
                    })
        }
        alert!!.show()
    }
}

