package vn.aquavietnam.aquaiget.service

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.aquavietnam.aquaiget.R
import vn.aquavietnam.aquaiget.ServiceBinding
import vn.aquavietnam.aquaiget.base.BaseFragment
import vn.aquavietnam.aquaiget.service.registerbusinesstrip.RegisterBusinessTripFragment
import vn.aquavietnam.aquaiget.service.registerdateoff.RegisterDateOffFragment
import vn.aquavietnam.aquaiget.service.registernonscan.RegisterNonScanFragment
import vn.aquavietnam.aquaiget.service.registerovertime.RegisterOverTimeFragment


class ServiceFragment : BaseFragment() {

    private var serviceBinding: ServiceBinding? = null
    private var serviceVM: ServiceVM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (serviceBinding == null) {
            serviceBinding = DataBindingUtil.inflate(inflater, R.layout.frag_service, container, false)
        }
        return serviceBinding!!.root
    }
    override fun findData() {
        serviceVM = ServiceVM(context)
        serviceBinding!!.serviceVM = serviceVM
        serviceVM!!.serviceAdapter.rowSelectOneParameter = {index -> Unit
            gotoFragment(index)
        }
    }

    fun gotoFragment(postion: Int) {
        if(postion == 0) {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, RegisterDateOffFragment(),"RegisterDateOffFragment").addToBackStack("RegisterDateOffFragment").commit()
        }else if(postion == 1) {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, RegisterOverTimeFragment(),"RegisterOverTimeFragment").addToBackStack("RegisterOverTimeFragment").commit()
        }else if(postion == 2) {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, RegisterNonScanFragment(),"RegisterNonScanFragment").addToBackStack("RegisterNonScanFragment").commit()
        }else if(postion == 3) {
            this.getBaseActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.tabbarContent, RegisterBusinessTripFragment(),"RegisterBusinessTripFragment").addToBackStack("RegisterBusinessTripFragment").commit()
        }
    }

}
