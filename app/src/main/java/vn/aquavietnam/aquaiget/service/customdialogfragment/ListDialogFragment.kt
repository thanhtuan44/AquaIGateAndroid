package vn.aquavietnam.aquaiget.service.customdialogfragment

import android.app.ActionBar
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.RequiresApi
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.DiaLogBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.model.DataBase
import vn.aquavietnam.aquaiget.model.Manager
import vn.aquavietnam.aquaiget.model.ManagerInfo
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus
import java.util.*
import android.util.DisplayMetrics
import android.view.*


/**
 * Created by ThanhTuan on 5/9/2018.
 */

class ListDialogFragment : DialogFragment() {

//    var vwLoading : ViewLoading? = null
    private var dialogBinding: DiaLogBinding? = null
    private var dialogVM: ListDiaLogVM? = null
    private var listDialogDisposable: Disposable? = null
    var dataSelected : DataBase?  = null
    var dataManagerSelected : ManagerInfo?  = null
    var dataFromDialog: ((DataBase) -> Unit)? =  null
    var dataFromDialogManager: ((ManagerInfo) -> Unit)? =  null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (dialogBinding == null) {
            dialogBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_dialog, container, false)
        }
        return dialogBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogVM = ListDiaLogVM(activity,arguments?.getParcelableArrayList("DATAEMPLOYEE"),arguments?.getString("ID_EMPLOYEE"))
        dialogBinding!!.dialogVM = dialogVM

        if(arguments?.getString("TITLE") != null) {
            val title =  arguments?.getString("TITLE") as String
            dialogBinding!!.lblTitle.text = title
        }
        if(arguments?.getString("ID_EMPLOYEE") != null) {
//            vwLoading = ViewLoading(activity,null,0)
//            vwLoading!!.showLoading(dialogBinding!!.cstLayoutBase)
            val idManager =  arguments?.getString("ID_EMPLOYEE") as String
            dialogVM!!.getListManager(idManager)
        }

        if(dialogVM!!.dialogAdapter != null) {
            dialogVM!!.dialogAdapter!!.rowSelectDiaLog = {data,index -> Unit
                dataSelected  = data as DataBase
                dialogVM!!.dialogAdapter!!.indexSelected = index
                dialogVM!!.dialogAdapter!!.notifyDataSetChanged()
            }
        }
        if( dialogVM!!.dialogAdapterManager != null) {
            dialogVM!!.dialogAdapterManager!!.rowSelectDiaLogManager = {data,index -> Unit
                dataManagerSelected  = data as ManagerInfo
                dialogVM!!.dialogAdapterManager!!.indexSelected = index
                dialogVM!!.dialogAdapterManager!!.notifyDataSetChanged()
            }
        }
        dialogBinding!!.btnDone.setOnClickListener {
            if(this != null){
                if(dialogVM!!.dialogAdapter != null) {
                    if(dataSelected != null) {
                        if(dialogVM!!.dialogAdapter!!.indexSelected != -1){
                            this.dataFromDialog?.invoke(dataSelected!!)
                        }
                    }
                    this.dismiss()
                }
                if(dialogVM!!.dialogAdapterManager != null) {
                    if(dataManagerSelected != null) {
                        if(dialogVM!!.dialogAdapterManager!!.indexSelected != -1){
                            this.dataFromDialogManager?.invoke(dataManagerSelected!!)
                        }
                    }
                    this.dismiss()
                }
            }
        }
        dialogBinding!!.btnClose.setOnClickListener {
            if(this != null){
                this.dismiss()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        listDialogDisposable = RxBus.instance.observableWithEvent(DataBus.GET_LIST_MANAGER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
//                        vwLoading!!.hideLoading(dialogBinding!!.cstLayoutBase)
                        val manager: Manager = o.eventValue as Manager
                        Log.d("Manager Info",manager.toString())
                        if (manager != null) {
                            if (context != null) {
                                if(manager.managerInfo != null) {
                                    dialogVM!!.setAdapterManager(manager.managerInfo!! as MutableList<ManagerInfo>)
                                }
                            }
                        }
                    }
                })
    }
    fun newInstance(arrData: ArrayList<DataBase>?,id: String?,title: String?): ListDialogFragment {
        val fragment = ListDialogFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList("DATAEMPLOYEE",arrData)
        bundle.putString("ID_EMPLOYEE",id)
        bundle.putString("TITLE",title)
        fragment.arguments = bundle
        return fragment
    }


    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        if(dialog != null) {
            dialogBinding = null
            dialog!!.cancel()
        }
    }
    override fun onPause() {
        super.onPause()
        if(listDialogDisposable != null) {
            if (listDialogDisposable!!.isDisposed) {
                listDialogDisposable!!.dispose()
            }
        }
    }
}
