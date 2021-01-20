package com.gt.entrypad.module.project.ui.view.photoView

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gt.entrypad.R
import com.gt.entrypad.base.view.BaseCustomView
import com.gt.entrypad.databinding.LayoutPhotoViewBinding
import java.io.File

class PhotoView  @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr:Int = 0): BaseCustomView<LayoutPhotoViewBinding, PhotoViewViewModel>(context, attrs, defStyleAttr){
    override fun getLayoutId(): Int {
        return R.layout.layout_photo_view
    }

    override fun onRootClick(view: View) {

    }

    override fun setDataToView(data: PhotoViewViewModel) {
        getDataBinding().viewModel = data
        val uri = Uri.fromFile(File("/storage/emulated/0/houseSketch/HouseFile/20210120_233444.jpg"))
        Glide.with(context)
            .load(if (data.url.isEmpty()&&data.resId!=0)data.resId else if(data.url.startsWith("http")) data.url else uri)
            .apply(
                RequestOptions()
                    .centerCrop()
                    .placeholder(com.zx.zxutils.R.drawable.__picker_default_weixin)
                    .error(com.zx.zxutils.R.mipmap.__picker_ic_broken_image_black_48dp)
            )
            .thumbnail(0.1f)
            .into(getDataBinding().photoViewIv)

    }

    override fun setStyle(resId: Int) {

    }

}