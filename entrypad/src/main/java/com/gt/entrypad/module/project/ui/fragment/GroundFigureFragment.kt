package com.gt.entrypad.module.project.ui.fragment

import android.os.Bundle
import com.gt.entrypad.R
import com.gt.entrypad.base.BaseFragment
import com.gt.entrypad.module.project.mvp.contract.GroundFigureContract
import com.gt.entrypad.module.project.mvp.model.GroundFigureModel
import com.gt.entrypad.module.project.mvp.presenter.GroundFigureresenter

/**
 * create by 96212 on 2021/1/22.
 * Email 962123525@qq.com
 * desc 宗地落图
 */
class GroundFigureFragment :BaseFragment<GroundFigureresenter,GroundFigureModel>(),GroundFigureContract.View{

    companion object {
        /**
         * 启动器
         */
        fun newInstance(): GroundFigureFragment {
            val fragment = GroundFigureFragment()
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