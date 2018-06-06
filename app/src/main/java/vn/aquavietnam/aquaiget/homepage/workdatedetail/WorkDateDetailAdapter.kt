package vn.aquavietnam.aquaiget.homepage.workdatedetail

import android.database.DataSetObserver
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by ThanhTuan on 5/7/2018.
 */
class WorkDateDetailAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    val fragments : ArrayList<Fragment> = ArrayList()
    val titles : ArrayList<String> = ArrayList()
    override fun getItem(position: Int): Fragment = fragments.get(position)

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles.get(position)

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }


}