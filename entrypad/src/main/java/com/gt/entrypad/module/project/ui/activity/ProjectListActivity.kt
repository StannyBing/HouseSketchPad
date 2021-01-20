package com.gt.entrypad.module.project.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gt.entrypad.R
import com.gt.entrypad.app.RouterPath
import com.gt.entrypad.base.BaseActivity
import com.gt.entrypad.base.view.ICustomViewActionListener
import com.gt.entrypad.base.viewModel.BaseCustomViewModel

import com.gt.entrypad.module.project.mvp.contract.ProjectListContract
import com.gt.entrypad.module.project.mvp.model.ProjectListModel
import com.gt.entrypad.module.project.mvp.presenter.ProjectListPresenter
import com.gt.entrypad.module.project.ui.view.titleView.TitleViewViewModel
import com.zx.zxutils.util.ZXToastUtil
import com.zx.zxutils.views.ZXStatusBarCompat
import kotlinx.android.synthetic.main.activity_project_list.*
import kotlinx.android.synthetic.main.layout_tool_bar.*
import java.io.File


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
@Route(path = RouterPath.PROJECT_LIST)
class ProjectListActivity : BaseActivity<ProjectListPresenter, ProjectListModel>(), ProjectListContract.View {

    override fun onViewListener() {

    }

	/**
     * layout配置
     */
	override fun getLayoutId(): Int {
        return R.layout.activity_project_list
    }
	
	/**
     * 初始化
     */
	override fun initView(savedInstanceState: Bundle?) {
        ZXStatusBarCompat.translucent(this)
        ZXStatusBarCompat.setStatusBarLightMode(this)
        leftTv.apply {
           setData(TitleViewViewModel(getString(R.string.createProject)))
           setActionListener(object :ICustomViewActionListener{
               override fun onAction(action: String, view: View, viewModel: BaseCustomViewModel) {
                   ProjectHomePageActivity.startAction(this@ProjectListActivity,false)
               }

           })
       }
        toolBarTitleTv.text = getString(R.string.registrationList)
       getPermission(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)){
           val uri = Uri.fromFile(File("/storage/emulated/0/houseSketch/HouseFile/20210120_233444.jpg"))
           Glide.with(mContext)
               .load(uri)
               .apply(
                   RequestOptions()
                       .centerCrop()
                       .placeholder(com.zx.zxutils.R.drawable.__picker_default_weixin)
                       .error(com.zx.zxutils.R.mipmap.__picker_ic_broken_image_black_48dp)
               )
               .thumbnail(0.1f)
               .into(iv)
       }
    }

}
