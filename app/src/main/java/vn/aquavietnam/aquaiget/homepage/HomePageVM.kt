package vn.aquavietnam.aquaiget.homepage

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.Category
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.DataBus.Companion.GET_HOME1_EVENT
import vn.aquavietnam.aquaiget.rxjava.RxBus
import java.util.ArrayList

/**
 * Created by ThanhTuan on 3/27/2018.
 */
class HomePageVM (context: Context?) : BaseVM() {
    var categoryAdapter: HomePageAdapter
    init {
        this.context = context
        categoryAdapter = HomePageAdapter(context!!,createData())
        initRecycler(categoryAdapter,3)
    }

    private fun createData(): List<Category> {
        val categories = ArrayList<Category>()
        categories.add(Category(R.drawable.iconworkdate,"Working daily"))
        categories.add(Category(R.drawable.iconbonus,"Bonus"))
        categories.add(Category(R.drawable.iconfreedate,"Annualleave"))
        categories.add(Category(R.drawable.iconnonscan,"Missing NonScan"))

        return categories
    }

    fun getHome1() {
        AquaService.getHome1Service().execute().subscribe({ home1 ->
            if (home1 != null) {
                Log.v("HomePageFragment", "3213")
                val dataBus = DataBus(GET_HOME1_EVENT, home1)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("HomePageFragment", error.message)
        })
    }
}