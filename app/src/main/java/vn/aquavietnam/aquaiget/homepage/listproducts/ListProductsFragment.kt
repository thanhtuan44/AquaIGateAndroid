package vn.aquavietnam.aquaiget.homepage.listproducts

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.ListProductsBinding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.category.CategoryFragment
import vn.aquavietnam.aquaiget.homepage.productaquadetail.ProductAquaDetailFragment

class ListProductsFragment: BaseBackFragment() {
    private var prod : Int? = 0
    private var url : String? = ""
    private var listProductsBinding: ListProductsBinding? = null
    private var listProductsVM: ListProductsVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (listProductsBinding == null) {
            listProductsBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_products, container, false)
        }
        return listProductsBinding!!.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prod = arguments?.getInt("PRODUCT")
        url = arguments?.getString("URL")
    }
    override fun findData() {
        showLoading(listProductsBinding!!.cstListProduct)
        listProductsVM = ListProductsVM(context!!,url!!,prod!!)
        listProductsBinding!!.listProductsVM = listProductsVM
        listProductsVM!!.listProductsAdapter?.rowSelectMultiParameters = { urlDetail, urlParent, product -> Unit
            gotoFragment(urlDetail, urlParent, product)
        }
    }

    override fun callService() {

    }
    override fun onResume() {
        super.onResume()
        listProductsVM!!.listProductsAqua = { listProductAqua ->
            if(isAdded){
                getBaseActivity().runOnUiThread {
                    hideLoading(listProductsBinding!!.cstListProduct)
                    listProductsVM!!.setAdapter(listProductAqua)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }
    fun gotoFragment(urlDetail: String, urlParent: String, product: Int) {
        val bundle = Bundle()
        bundle.putInt("PRODUCT",product)
        bundle.putString("URLDETAIL",urlDetail)
        bundle.putString("URLPARENT",urlParent)
        val productDetailFragment = ProductAquaDetailFragment()
        productDetailFragment.arguments = bundle
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, productDetailFragment,"ProductAquaDetailFragment").addToBackStack("ListProductFragment").commit()
    }
    override fun backView() {
        val bundle = Bundle()
        bundle.putInt("INDEX",prod!!)
        val categoryFragment = CategoryFragment()
        categoryFragment.arguments = bundle
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.tabbarContent, categoryFragment,"CategoryFragment").addToBackStack("CategoryFragment").commit()
    }
}