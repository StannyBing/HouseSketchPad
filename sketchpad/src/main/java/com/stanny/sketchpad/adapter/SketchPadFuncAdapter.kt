package com.stanny.sketchpad.adapter

import com.stanny.sketchpad.R
import com.stanny.sketchpad.bean.SketchPadFuncBean
import com.zx.zxutils.other.QuickAdapter.ZXBaseHolder
import com.zx.zxutils.other.QuickAdapter.ZXQuickAdapter

class SketchPadFuncAdapter(var dataList: List<SketchPadFuncBean>) :
    ZXQuickAdapter<SketchPadFuncBean, ZXBaseHolder>(R.layout.item_func_layout, dataList) {
    override fun convert(helper: ZXBaseHolder, item: SketchPadFuncBean) {
        helper.setBackgroundRes(R.id.iv_func_icon, item.icon)
        helper.setText(R.id.tv_func_name, item.name)
    }
}