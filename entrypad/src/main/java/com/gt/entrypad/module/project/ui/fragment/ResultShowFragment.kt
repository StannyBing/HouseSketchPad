package com.gt.entrypad.module.project.ui.fragment

import android.os.Bundle
import com.gt.entrypad.R
import com.gt.entrypad.base.BaseFragment
import com.gt.entrypad.module.project.mvp.contract.ResultShowContract
import com.gt.entrypad.module.project.mvp.model.ResultShowModel
import com.gt.entrypad.module.project.mvp.presenter.ResultShowPresenter

/**
 * create by 96212 on 2021/1/22.
 * Email 962123525@qq.com
 * desc  成果展示
 */
class ResultShowFragment :BaseFragment<ResultShowPresenter,ResultShowModel>(),ResultShowContract.View{

    companion object {
        /**
         * 启动器
         */
        fun newInstance(): ResultShowFragment {
            val fragment = ResultShowFragment()
            val bundle = Bundle()

            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onViewListener() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_result_show
    }

}
