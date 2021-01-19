package com.gt.entrypad.module.project.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.frame.zxmvp.base.BaseModel
import com.frame.zxmvp.base.BasePresenter
import com.frame.zxmvp.base.IView
import com.gt.entrypad.R
import com.gt.entrypad.base.BaseActivity
import com.lt.component_library.app.ConstStrings
import com.zx.zxutils.util.ZXTimeUtil
import com.zx.zxutils.views.CameraView.ZXCameraView
import com.zx.zxutils.views.CameraView.listener.CameraListener
import com.zx.zxutils.views.PhotoPicker.PhotoPicker
import kotlinx.android.synthetic.main.activity_acmera.*
import java.text.SimpleDateFormat


class CameraActivity :BaseActivity<BasePresenter<*,*>,BaseModel>(),IView{


    companion object {
        /**
         * 启动器
         */
        fun startAction(activity: Activity, isFinish: Boolean, cameraType: Int = 0) {
            val intent = Intent(activity, CameraActivity::class.java)
            intent.putExtra("cameraType", cameraType)
            activity.startActivityForResult(intent, PhotoPicker.REQUEST_CODE)
            if (isFinish) activity.finish()
        }
    }
    override fun onViewListener() {

    }
    override fun getLayoutId(): Int {
        return R.layout.activity_acmera
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        //设置视频保存路径
        cameraView?.apply {
            setSaveVideoPath(ConstStrings.getLocalPath() + "HouseFile")
            setCameraMode(ZXCameraView.BUTTON_STATE_ONLY_CAPTURE)
            setMediaQuality(ZXCameraView.MEDIA_QUALITY_MIDDLE)
            setMaxVedioDuration(30)
            showAlbumView(true)
            setCameraLisenter(object : CameraListener {
                override fun onCaptureCommit(bitmap: Bitmap) {
                    val time = ZXTimeUtil.getTime(System.currentTimeMillis(), SimpleDateFormat("yyyyMMdd_HHmmss"))
                    val name = time
                    val path = ConstStrings.getLocalPath() + "HouseFile/" + time + ".jpg"
                    val intent = Intent()
                    intent.putExtra("type", 1)
                    intent.putExtra("path", path)
                    intent.putExtra("name", name)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }

                override fun onRecordCommit(url: String, firstFrame: Bitmap) {}

                override fun onActionSuccess(type: CameraListener.CameraType) {

                }

                override fun onError(errorType: CameraListener.ErrorType) {
                    //打开Camera失败回调
                }
            })
        }
    }
    override fun onResume() {
        super.onResume()
        cameraView.onResume()
    }

    override fun onPause() {
        super.onPause()
        cameraView.onPause()
    }
}