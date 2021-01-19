package com.gt.entrypad.module.project.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gt.entrypad.R
import com.gt.entrypad.base.BaseFragment
import com.gt.entrypad.base.view.ICustomViewActionListener
import com.gt.entrypad.base.viewModel.BaseCustomViewModel
import com.gt.entrypad.module.project.func.BottomSheetAdapter
import com.gt.entrypad.module.project.mvp.contract.ScanIdCardContract
import com.gt.entrypad.module.project.mvp.model.ScanIdCardModel
import com.gt.entrypad.module.project.mvp.presenter.ScanIdCardPresenter
import com.gt.entrypad.module.project.ui.view.titleView.TitleView
import com.gt.entrypad.module.project.ui.view.titleView.TitleViewViewModel
import kotlinx.android.synthetic.main.fragment_scan_id_card.*
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.gt.entrypad.app.RouterPath
import com.gt.entrypad.module.project.ui.view.BottomSheetOptionsDialog

@Route(path = RouterPath.SCANID_CARD)
class ScanIdCardFragment :BaseFragment<ScanIdCardPresenter,ScanIdCardModel>(),ScanIdCardContract.View{
    companion object {
        /**
         * 启动器
         */
        fun newInstance(): ScanIdCardFragment {
            val fragment = ScanIdCardFragment()
            val bundle = Bundle()

            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onViewListener() {
        floatActionButton.setOnClickListener {
            BottomSheetOptionsDialog(mContext,initBottomData()).show()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_scan_id_card
    }

    private fun initBottomData():List<TitleViewViewModel>{
       return arrayListOf<TitleViewViewModel>().apply {
            add(TitleViewViewModel(getString(R.string.scanIdCard)))
            add(TitleViewViewModel(getString(R.string.inputMsg)))
        }
    }

}