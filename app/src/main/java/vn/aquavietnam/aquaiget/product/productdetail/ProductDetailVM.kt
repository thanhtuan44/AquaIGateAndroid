package vn.aquavietnam.aquaiget.product.productdetail

import android.content.Context
import android.util.Log
import vn.aquavietnam.aquaiget.base.BaseVM
import vn.aquavietnam.aquaiget.model.Product
import vn.aquavietnam.aquaiget.model.ProductDetail
import vn.aquavietnam.aquaiget.model.ProductDetailFeature
import vn.aquavietnam.aquaiget.network.AquaService
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/25/2018.
 */
class ProductDetailVM(context: Context?, arrProduct: List<ProductDetailFeature>?): BaseVM()  {
    var productDetailAdapter: ProductDetailAdapter? = null

    init {
        this.context = context
        productDetailAdapter = ProductDetailAdapter(context!!, arrProduct)
        initRecycler(productDetailAdapter!!,1)
    }
    fun setAdapter(listProduct: List<ProductDetailFeature>?) {
        productDetailAdapter!!.arrData = listProduct
        productDetailAdapter!!.notifyDataSetChanged()
    }
    fun getProduct(productID: String) {
        Log.d("TEXT","VALUE")
        AquaService.getProductDetailService().setIdProduct(productID).execute().subscribe({ listProduct ->
            if (listProduct != null) {
                Log.v("ProductPageFragment", "3213")
                val dataBus = DataBus(DataBus.GET_PRODUCT_DETAIL, listProduct)
                RxBus.instance.send(dataBus)
            }
        }, { error ->
            Log.v("ProductPageFragment", error.message)
        })
    }
}