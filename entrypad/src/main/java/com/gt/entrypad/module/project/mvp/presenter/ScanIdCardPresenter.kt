package com.gt.entrypad.module.project.mvp.presenter

import com.frame.zxmvp.baserx.RxHelper
import com.frame.zxmvp.baserx.RxSubscriber
import com.gt.entrypad.module.project.mvp.contract.ScanIdCardContract
import okhttp3.RequestBody


/**
 * Create By admin On 2017/7/11
 * 功能：
 */
class ScanIdCardPresenter : ScanIdCardContract.Presenter() {
    override fun scanIdCard(body:RequestBody) {
            mModel.scanIdCard(body)
                .compose(RxHelper.bindToLifecycle(mView))
                .subscribe(object : RxSubscriber<String>(mView) {
                    override fun _onError(code: Int, message: String?) {
                        mView.handleError(code, message?:"")
                        mView.dismissLoading()
                    }

                    override fun _onNext(t: String?) {
                      mView.scanIdCardResult(t)
                    }
                })
    }
}