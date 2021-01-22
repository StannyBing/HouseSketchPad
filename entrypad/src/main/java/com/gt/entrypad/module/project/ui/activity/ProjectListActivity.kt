package com.gt.entrypad.module.project.ui.activity

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.baidu.ocr.sdk.OCR
import com.baidu.ocr.sdk.OnResultListener
import com.baidu.ocr.sdk.exception.OCRError
import com.baidu.ocr.sdk.model.AccessToken
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gt.entrypad.app.RouterPath
import com.gt.entrypad.base.BaseActivity
import com.gt.entrypad.base.view.ICustomViewActionListener
import com.gt.entrypad.base.viewModel.BaseCustomViewModel

import com.gt.entrypad.module.project.mvp.contract.ProjectListContract
import com.gt.entrypad.module.project.mvp.model.ProjectListModel
import com.gt.entrypad.module.project.mvp.presenter.ProjectListPresenter
import com.gt.entrypad.module.project.ui.view.titleView.TitleViewViewModel
import com.zx.zxutils.views.ZXStatusBarCompat
import kotlinx.android.synthetic.main.activity_project_list.*
import kotlinx.android.synthetic.main.layout_tool_bar.*
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.gt.entrypad.R
import com.zx.zxutils.util.ZXToastUtil

/**
 * Create By admin On 2017/7/11
 * 功能：
 */
@Route(path = RouterPath.PROJECT_LIST)
class ProjectListActivity : BaseActivity<ProjectListPresenter, ProjectListModel>(), ProjectListContract.View {
    private var hasGotToken = false
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
        initAccessTokenWithAkSk(this)
    }
    /**
     * 用明文ak,sk初始化
     */
    private fun initAccessTokenWithAkSk(context: Context) {
        OCR.getInstance(context).initAccessTokenWithAkSk(object : OnResultListener<AccessToken> {
            override fun onResult(p0: AccessToken?) {
                val token = p0?.accessToken ?: ""
                hasGotToken = true
            }

            override fun onError(p0: OCRError?) {
                showToast("AK，SK方式获取token失败${p0?.message}")
            }

        }, context, "A9ja6msDU0GhGusChhtRwZMh", "y2oGeYHrtnkCidGcMIKlSOI4QmlIvdg6")
    }
    fun getToken():Boolean{
        return  hasGotToken
    }
}

