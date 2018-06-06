package vn.aquavietnam.aquaiget.product

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.Product
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.product.ProductAdapter
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

class ProductVM(context: Context?, listProduct: List<Product>?): BaseVM()  {
    var productAdapter: ProductAdapter? = null
    init {
        this.context = context
        productAdapter = ProductAdapter(context!!, listProduct)
        initRecycler(productAdapter!!,2)
    }
    fun setAdapter(listProduct: List<Product>?) {
        productAdapter!!.arrData = listProduct
        productAdapter!!.notifyDataSetChanged()
    }
    fun getProduct() {
        Log.d("TEXT","VALUE")
        AquaService.getProductService().execute().subscribe({ listProduct ->
            if (listProduct != null) {
                Log.v("ProductPageFragment", "3213")
                val dataBus = DataBus(DataBus.GET_PRODUCT_EVENT, listProduct)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("ProductPageFragment", error.message)
        })
    }
}