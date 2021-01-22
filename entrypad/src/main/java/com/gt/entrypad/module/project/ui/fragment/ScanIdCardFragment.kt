package com.gt.entrypad.module.project.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.gt.entrypad.R
import com.gt.entrypad.module.project.mvp.contract.ScanIdCardContract
import com.gt.entrypad.module.project.mvp.model.ScanIdCardModel
import com.gt.entrypad.module.project.mvp.presenter.ScanIdCardPresenter
import com.gt.entrypad.module.project.ui.view.titleView.TitleViewViewModel
import kotlinx.android.synthetic.main.fragment_scan_id_card.*
import com.alibaba.android.arouter.facade.annotation.Route
import com.baidu.ocr.sdk.OCR
import com.baidu.ocr.sdk.OnResultListener
import com.baidu.ocr.sdk.exception.OCRError
import com.baidu.ocr.sdk.model.IDCardParams
import com.baidu.ocr.sdk.model.IDCardResult
import com.baidu.ocr.ui.camera.CameraActivity
import com.baidu.ocr.ui.camera.CameraNativeHelper
import com.baidu.ocr.ui.camera.CameraView
import com.gt.entrypad.app.RouterPath
import com.gt.entrypad.base.BaseFragment
import com.gt.entrypad.base.BaseLazyFragment
import com.gt.entrypad.module.project.ui.view.BottomSheetOptionsDialog
import com.gt.entrypad.module.project.ui.view.photoView.PhotoViewViewModel
import com.gt.entrypad.tool.FileUtil
import com.zx.zxutils.util.ZXScreenUtil
import rx.functions.Action1
import java.io.File

@Route(path = RouterPath.SCANID_CARD)
class ScanIdCardFragment : BaseFragment<ScanIdCardPresenter, ScanIdCardModel>(),
    ScanIdCardContract.View {
    private var bottomSheetOptionsDialog: BottomSheetOptionsDialog? = null
    private val REQUEST_CODE_CAMERA = 102

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
            BottomSheetOptionsDialog(mContext, initBottomData()).apply {
                bottomSheetOptionsDialog = this
                show()
            }
        }
        mRxManager.on("bottom", Action1<String> {
            bottomSheetOptionsDialog?.dismiss()
            when (it) {
                getString(R.string.scanIdCard) -> {
                    //扫描正面
                    Intent(mActivity, CameraActivity::class.java).apply {
                        putExtra(
                            CameraActivity.KEY_OUTPUT_FILE_PATH,
                            FileUtil.getSaveFile(mContext).absolutePath)
                        putExtra(CameraActivity.KEY_NATIVE_ENABLE, true)
                        // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                        // 请手动使用CameraNativeHelper初始化和释放模型
                        // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                        putExtra(CameraActivity.KEY_NATIVE_MANUAL, true)
                        putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT)
                        startActivityForResult(this, REQUEST_CODE_CAMERA)
                    }

                }
                getString(R.string.inputMsg) -> {

                }
            }
        })
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        //初始化本地质量控制模型
        CameraNativeHelper.init(mContext, OCR.getInstance(mContext).license) { errorCode, e ->
            var msg = when (errorCode) {
                CameraView.NATIVE_SOLOAD_FAIL -> "加载so失败，请确保apk中存在ui部分的so"
                CameraView.NATIVE_AUTH_FAIL -> "授权本地质量控制token获取失败"
                CameraView.NATIVE_INIT_FAIL -> "本地质量控制"
                else -> errorCode.toString()
            }
            showToast(msg)
        }
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_scan_id_card
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE)
                val filePath = FileUtil.getSaveFile(mContext).absolutePath
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT == contentType) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath)
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK == contentType) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath)
                    }
                }
            }
        }
    }

    private fun initBottomData(): List<TitleViewViewModel> {
        return arrayListOf<TitleViewViewModel>().apply {
            add(TitleViewViewModel(getString(R.string.scanIdCard)))
            add(TitleViewViewModel(getString(R.string.inputMsg)))
        }
    }

    override fun onDestroy() {
        // 释放本地质量控制模型
        CameraNativeHelper.release()
        super.onDestroy()
    }

    private fun recIDCard(idCardSide: String, filePath: String) {
        val param = IDCardParams()
        param.imageFile = File(filePath)
        // 设置身份证正反面
        param.idCardSide = idCardSide
        // 设置方向检测
        param.isDetectDirection = true
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.imageQuality = 20

        OCR.getInstance(mContext).recognizeIDCard(param, object : OnResultListener<IDCardResult> {
            override fun onResult(result: IDCardResult?) {
               result?.let {
                   photoView.apply {
                       visibility = View.VISIBLE
                       setData(PhotoViewViewModel(filePath,ZXScreenUtil.getScreenWidth()/2,ZXScreenUtil.getScreenWidth()/4))
                   }
               }
            }

            override fun onError(error: OCRError) {

            }
        })
    }
}