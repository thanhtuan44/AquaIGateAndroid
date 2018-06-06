package vn.aquavietnam.aquaiget.service

import android.content.Context
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.Category
import java.util.ArrayList

/**
 * Created by ThanhTuan on 5/4/2018.
 */
class ServiceVM (context: Context?) : BaseVM() {
    var serviceAdapter: ServiceAdapter

    init {
        this.context = context
        serviceAdapter = ServiceAdapter(context!!, createData())
        initRecycler(serviceAdapter, 2)
    }

    private fun createData(): List<Category> {
        val categories = ArrayList<Category>()
        categories.add(Category(R.drawable.icondateoff, "Đăng kí nghỉ phép"))
        categories.add(Category(R.drawable.iconovertime, "Đăng kí tăng ca"))
        categories.add(Category(R.drawable.iconnonscan, "Xác nhận quẹt thẻ"))
        categories.add(Category(R.drawable.iconbussinesstrip, "Xác nhận đi công tác"))
        return categories
    }
}