package com.gt.entrypad.module.project.ui.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gt.entrypad.R
import com.gt.entrypad.app.RouterPath
import com.gt.entrypad.base.BaseFragment
import com.gt.entrypad.module.project.mvp.contract.TakePhotoContract
import com.gt.entrypad.module.project.mvp.model.TakePhotoModel
import com.gt.entrypad.module.project.mvp.presenter.TakePhotoPresenter
import com.gt.entrypad.module.project.ui.activity.CameraActivity
import com.gt.entrypad.module.project.ui.view.BottomSheetOptionsDialog
import com.gt.entrypad.module.project.ui.view.titleView.TitleViewViewModel
import com.zx.zxutils.views.PhotoPicker.PhotoPicker
import com.zx.zxutils.views.PhotoPicker.listener.OnDeleteListener
import com.zx.zxutils.views.PhotoPicker.widget.ZXPhotoPickerView
import kotlinx.android.synthetic.main.fragment_take_photo.*
import rx.functions.Action1

@Route(path =RouterPath.TAKE_PHOTO)
class TakePhotoFragment :BaseFragment<TakePhotoPresenter,TakePhotoModel>(),TakePhotoContract.View{
   private var photoList= arrayListOf<String>()
    private var bottomSheetOptionsDialog:BottomSheetOptionsDialog?=null
    companion object {
        /**
         * 启动器
         */
        fun newInstance(): TakePhotoFragment {
            val fragment = TakePhotoFragment()
            val bundle = Bundle()

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        photoPickerView.init(mActivity,ZXPhotoPickerView.ACTION_DELETE,photoList, OnDeleteListener {
            photoList.removeAt(it)
            photoPickerView.photoAdapter.refresh(photoList,false)
        })
    }
    override fun onViewListener() {
        photoAddIv.setOnClickListener {
            BottomSheetOptionsDialog(mContext, arrayListOf<TitleViewViewModel>().apply {
                add(TitleViewViewModel(getString(R.string.photo)))
                add(TitleViewViewModel(getString(R.string.camera)))
                add(TitleViewViewModel(getString(R.string.sketch)))
            }).apply {
                bottomSheetOptionsDialog =this
                show()
            }
        }
        mRxManager.on("bottom", Action1<String> {
            bottomSheetOptionsDialog?.dismiss()
            when(it){
                getString(R.string.photo)->{
                    //相册
                }
                getString(R.string.camera)->{
                    //拍照
                    getPermission(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        CameraActivity.startAction(mActivity, false, 1)
                    }
                }
                getString(R.string.sketch)->{
                    //拍摄草图
                }
            }
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_take_photo
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== PhotoPicker.REQUEST_CODE&&resultCode== Activity.RESULT_OK){
            photoPickerView.onActivityResult(requestCode, resultCode, data)
            photoList.add(data?.getStringExtra("path")?:"")
            photoPickerView.photoAdapter.
        }
    }
}