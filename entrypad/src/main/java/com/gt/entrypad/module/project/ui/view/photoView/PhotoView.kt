package com.gt.entrypad.module.project.ui.view.photoView

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.gt.entrypad.R
import com.gt.entrypad.base.view.BaseCustomView
import com.gt.entrypad.databinding.LayoutPhotoViewBinding
import kotlinx.android.synthetic.main.activity_project_list.*
import java.io.File

class PhotoView  @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr:Int = 0): BaseCustomView<LayoutPhotoViewBinding, PhotoViewViewModel>(context, attrs, defStyleAttr){
    override fun getLayoutId(): Int {
        return R.layout.layout_photo_view
    }

    override fun onRootClick(view: View) {

    }

    override fun setDataToView(data: PhotoViewViewModel) {
        getDataBinding().viewModel = data
        Glide.with(context)
            .load(if (data.url.isEmpty()&&data.resId!=0){
                data.resId
            } else data.url)
            .apply(
                RequestOptions()
                    .centerCrop()
                    .placeholder(com.zx.zxutils.R.drawable.__picker_default_weixin)
                    .error(R.drawable.icon_img_error)
            )
            .into(getDataBinding().photoViewIv)
        getDataBinding().photoViewDeleteIv.visibility = if (data.resId!=0) View.GONE else View.VISIBLE
    }

    override fun setStyle(resId: Int) {

    }

}