package vn.aquavietnam.aquaiget.product.productdetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.aquavietnam.aquaiget.ProductDetailBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.model.*
import vn.aquavietnam.aquaiget.product.ProductFragment
import vn.aquavietnam.aquaiget.rxjava.DataBus
import vn.aquavietnam.aquaiget.rxjava.RxBus

/**
 * Created by ThanhTuan on 5/25/2018.
 */
class ProductDetailFragment : BaseBackFragment()  {
    private var productDetailBinding: ProductDetailBinding? = null
    private var productDetailVM: ProductDetailVM? = null
    private var idProduct : String = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (productDetailBinding == null) {
            productDetailBinding = DataBindingUtil.inflate(inflater, R.layout.frag_product_detail, container, false)
        }

        return productDetailBinding!!.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idProduct = arguments!!.getString("PRODUCTID")
    }
    override fun findData() {
        showLoading(productDetailBinding!!.cstProductDetail)
        productDetailBinding!!.rclvCategory.isFocusable = false
        productDetailBinding!!.sldBanner.requestFocus()
        productDetailVM = ProductDetailVM(context,null)

        productDetailBinding!!.productDetailVM = productDetailVM

    }

    override fun callService() {
        if(idProduct != ""){
            productDetailVM!!.getProduct(idProduct)
        }
    }
    override fun onResume() {
        super.onResume()
        baseDisposable = RxBus.instance.observableWithEvent(DataBus.GET_PRODUCT_DETAIL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ o ->
                    if (o is DataBus) {
                        hideLoading(productDetailBinding!!.cstProductDetail)
                        val productDetail: ProductDetail = o.eventValue as ProductDetail
                        Log.d("DATA",productDetail.dataProductDetail!!.feature!!.size.toString())
                        Log.d("DATA",productDetail.dataProductDetail!!.images!!.size.toString())
                        val arrSlider : ArrayList<Slider>? = arrayListOf()
                        val arrFeatures : ArrayList<ProductDetailFeature>? = arrayListOf()
                        for (images: String in productDetail.dataProductDetail!!.images!!) {
                            if(images != null) {
                                val slider = Slider()
                                slider.image = images
                                arrSlider!!.add(slider)
                            }
                        }
                        if(arrSlider!!.size > 0) {
                            productDetailBinding!!.sldBanner?.sliderPageAdapter?.listPage = arrSlider
                            productDetailBinding!!.sldBanner?.sliderPageAdapter?.notifyDataSetChanged()
                        }
                        for (features: String in productDetail.dataProductDetail!!.feature!!) {
                            if(features != null) {
                                val feature = ProductDetailFeature()
                                feature.data = features
                                arrFeatures!!.add(feature)
                            }
                        }
                        if(arrFeatures!!.size > 0) {
                            productDetailVM!!.setAdapter(arrFeatures)
                        }
                    }
                })
    }
    override fun onPause() {
        super.onPause()
    }

    override fun backView() {
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.tabbarContent, ProductFragment(),"ProductFragment").addToBackStack("ProductFragment").commit()
    }
}