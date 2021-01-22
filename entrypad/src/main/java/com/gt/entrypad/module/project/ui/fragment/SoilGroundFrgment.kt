package com.gt.entrypad.module.project.ui.fragment

import android.os.Bundle
import com.gt.entrypad.R
import com.gt.entrypad.base.BaseFragment
import com.gt.entrypad.module.project.mvp.contract.SoilGroundContract
import com.gt.entrypad.module.project.mvp.model.SoilGroundModel
import com.gt.entrypad.module.project.mvp.presenter.SoilGroundresenter

/**
 * create by 96212 on 2021/1/22.
 * Email 962123525@qq.com
 * desc 宗土落地
 */
class SoilGroundFrgment :BaseFragment<SoilGroundresenter,SoilGroundModel>(),SoilGroundContract.View{

    companion object {
        /**
         * 启动器
         */
        fun newInstance(): SoilGroundFrgment {
            val fragment = SoilGroundFrgment()
            val bundle = Bundle()

            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onViewListener() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_soil_ground
    }

}