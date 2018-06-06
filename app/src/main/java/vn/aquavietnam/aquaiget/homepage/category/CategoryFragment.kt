package vn.aquavietnam.aquaiget.homepage.category

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.CategoryProductBinding
import vn.aquavietnam.aquaiget.base.BaseBackFragment
import vn.aquavietnam.aquaiget.homepage.HomePageFragment
import vn.aquavietnam.aquaiget.homepage.listproducts.ListProductsFragment

class CategoryFragment : BaseBackFragment(){

    private var index : Int? = 0
    private var categoryProductBinding: CategoryProductBinding? = null
    private var categoryVM: CategoryVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (categoryProductBinding == null) {
            categoryProductBinding = DataBindingUtil.inflate(inflater, R.layout.frag_list_category_product, container, false)
        }

        return categoryProductBinding!!.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        index = arguments?.getInt("INDEX")
    }
    override fun findData() {
        showLoading(categoryProductBinding!!.cstCatagoryProduct)
        categoryVM = CategoryVM(context,index!!)
        categoryProductBinding!!.categoryVM = categoryVM
        categoryVM!!.categorytAdapter?.rowSelectMultiParameters = { nodata ,url, product -> Unit
            gotoFragment(url,product)
        }
    }

    override fun callService() {

    }
    override fun onResume() {
        super.onResume()
        hideLoading(categoryProductBinding!!.cstCatagoryProduct)
    }

    override fun onPause() {
        super.onPause()
    }
    fun gotoFragment(url: String,product: Int) {
        val bundle = Bundle()
        bundle.putInt("PRODUCT",product)
        bundle.putString("URL",url)
        val productInfoDetailFragment = ListProductsFragment()
        productInfoDetailFragment.arguments = bundle
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, productInfoDetailFragment,"CategoryFragment").addToBackStack("CategoryFragment").commit()
    }
    override fun backView() {
        this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.tabbarContent, HomePageFragment(),"HomePageFragment").addToBackStack("HomePageFragment").commit()
    }
}