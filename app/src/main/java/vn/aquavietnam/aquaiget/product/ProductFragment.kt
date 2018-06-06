package vn.aquavietnam.aquaiget.product

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.frag_list_product.*
import vn.aquavietnam.aquaiget.ProductBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseFragment
import vn.aquavietnam.aquaiget.model.ListProduct
import vn.aquavietnam.aquaiget.model.Product
import vn.aquavietnam.aquaiget.product.productdetail.ProductDetailFragment
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 03/22/2018.
 * Modified by TrungHau on 04/05/2018
 */
class ProductFragment : BaseFragment() {

    private var productBinding: ProductBinding? = null
    private var productVM: ProductVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (productBinding == null) {
            productBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_product, container, false)
        }
        return productBinding!!.root
    }
    override fun findData() {
        showLoading(cstProduct)
        productVM = ProductVM(context, null)
        productVM!!.getProduct()
        productBinding!!.productVM = productVM
        productVM!!.productAdapter?.selectProduct = { idProduct -> Unit
            gotoFragment(idProduct)
        }
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_PRODUCT_EVENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(cstProduct)
                        val listProd: ListProduct = o.eventValue as ListProduct
                        Log.d("DATA PRODUCT",listProd.toString())
                        if (listProd.data!!.isNotEmpty()) {
                            if (context != null) {
                                productVM!!.setAdapter(listProd.data!! as MutableList<Product>)
                            }
                        }
                    }
                })

    }
    fun gotoFragment(productID: String) {
        val bundle = Bundle()
        bundle.putString("PRODUCTID",productID)
        val productDetailFragment = ProductDetailFragment()
        productDetailFragment.arguments = bundle
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, productDetailFragment,"ProductDetailFragment").addToBackStack("ProductDetailFragment").commit()
    }
    override fun onPause() {
        super.onPause()
    }
}