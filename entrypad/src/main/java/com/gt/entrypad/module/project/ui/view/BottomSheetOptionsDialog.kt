package com.gt.entrypad.module.project.ui.view

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gt.entrypad.R
import com.gt.entrypad.base.view.ICustomViewActionListener
import com.gt.entrypad.base.viewModel.BaseCustomViewModel
import com.gt.entrypad.module.project.func.BottomSheetAdapter
import com.gt.entrypad.module.project.ui.view.titleView.TitleView
import com.gt.entrypad.module.project.ui.view.titleView.TitleViewViewModel
import com.gt.entrypad.tool.SimpleDecoration
import com.zx.zxutils.util.ZXToastUtil

class BottomSheetOptionsDialog(context: Context,var dataList:List<TitleViewViewModel>) :BottomSheetDialog(context){
    private var mContext = context
    private var bottomAdapter = BottomSheetAdapter(dataList)
    init {
        createView()
    }

    private fun createView(){
        val bottomSheetView = layoutInflater.inflate(R.layout.layout_bottom_sheet_options,null)
        setContentView(bottomSheetView)

        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
        bottomSheetBehavior.peekHeight = mContext.resources.getDimensionPixelSize(R.dimen.peekHeight)
        (bottomSheetView.parent as View).setBackgroundColor(mContext.resources.getColor(R.color.transparent))
        bottomSheetView.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(mContext)
            adapter = bottomAdapter
            addItemDecoration(SimpleDecoration(mContext))
        }
        bottomSheetView.findViewById<TitleView>(R.id.cancelTitleView).apply {
            setData(TitleViewViewModel(mContext.getString(R.string.cancel)))
            setActionListener(object : ICustomViewActionListener {
                override fun onAction(action: String, view: View, viewModel: BaseCustomViewModel) {
                   dismiss()
                }
            })
        }
    }
}