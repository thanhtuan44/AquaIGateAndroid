package vn.aquavietnam.aquaiget.homepage.productaquadetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import vn.aquavietnam.aquaiget.ProductDetailAquaBinding
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.listproducts.ListProductsFragment

class ProductAquaDetailFragment: BaseBackFragment() {
    private var prod : Int = 0
    private var url : String = ""
    private var urlParent : String = ""
    private var productAquaDetailBinding: ProductDetailAquaBinding? = null
    private var productAquaDetailVM: ProductAquaDetailVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (productAquaDetailBinding == null) {
            productAquaDetailBinding = DataBindingUtil.inflate(inflater, R.layout.frag_product_aqua_detail, container, false)
        }
        return productAquaDetailBinding!!.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prod = arguments!!.getInt("PRODUCT")
        url = arguments!!.getString("URLDETAIL")
        urlParent = arguments!!.getString("URLPARENT")
    }
    override fun findData() {
        productAquaDetailVM = ProductAquaDetailVM(context!!,url!!)
        productAquaDetailBinding!!.productAquaDetailVM = productAquaDetailVM
    }

    override fun callService() {
        showLoading(productAquaDetailBinding!!.cstProductAquaDetail)
        productAquaDetailVM!!.productDetail = { productDetail ->
            if(isAdded){
                getBaseActivity().runOnUiThread {
                    hideLoading(productAquaDetailBinding!!.cstProductAquaDetail)
                    productAquaDetailVM!!.setAdapter(productDetail.listInfo)
                    Glide.with(context)
                            .load(productDetail.image)
                            .into(productAquaDetailBinding!!.imvProduct)
                    productAquaDetailBinding!!.tvModel.setText(productDetail.model)
                    productAquaDetailBinding!!.tvInfo.setText(productDetail.info)
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }
    override fun backView() {
        val bundle = Bundle()
        bundle.putInt("PRODUCT",prod)
        bundle.putString("URL",urlParent)
        val listProductsFragment = ListProductsFragment()
        listProductsFragment.arguments = bundle
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.tabbarContent, listProductsFragment,"ListProductsFragment").addToBackStack("ListProductsFragment").commit()
    }
}