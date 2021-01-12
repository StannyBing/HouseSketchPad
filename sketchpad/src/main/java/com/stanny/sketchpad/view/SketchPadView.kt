package com.stanny.sketchpad.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.stanny.sketchpad.R
import com.stanny.sketchpad.bean.SketchPadGraphicBean
import com.stanny.sketchpad.listener.SketchPadListener
import com.zx.zxutils.util.ZXToastUtil
import kotlinx.android.synthetic.main.layout_sketchpad_view.view.*

/**
 * 房屋画板主View
 */
class SketchPadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), SketchPadListener {

    init {
        setWillNotDraw(false)
        View.inflate(context, R.layout.layout_sketchpad_view, this)

        initListener()
    }

    /**
     * 初始化监听
     */
    private fun initListener() {
        sketch_content.sketchPadListener = this
        sketch_func.sketchPadListener = this
        sketch_graphic.sketchPadListener = this
        sketch_propedit.sketchPadListener = this
    }

    /**
     * 图形插入
     */
    override fun graphicInsert(graphicBean: SketchPadGraphicBean) {
        sketch_content.insertGraphic(graphicBean)
    }

    /**
     * 图形编辑
     */
    override fun graphicEdit(graphicBean: SketchPadGraphicBean) {
        sketch_propedit.editGraphic(graphicBean)
    }

    /**
     * 关闭编辑
     */
    override fun closeEdit() {
        sketch_content.closeEdit()
    }

    /**
     * 刷新图形
     */
    override fun refreshGraphic() {
        sketch_content.refreshGraphic()
    }

    /**
     * 重置中央
     */
    override fun resetCenter() {
        sketch_content.resetCenter()
    }

    /**
     * 保存图形
     */
    override fun saveGraphicInfo() {
        resetCenter()
        sketch_content.saveGraphicInfo {
            ZXToastUtil.showToast("保存成功")
        }
    }
}