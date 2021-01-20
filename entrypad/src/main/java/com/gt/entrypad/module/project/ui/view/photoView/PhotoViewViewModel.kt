package com.gt.entrypad.module.project.ui.view.photoView

import android.view.View
import com.frame.zxmvp.baserx.RxManager
import com.gt.entrypad.R
import com.gt.entrypad.base.viewModel.BaseCustomViewModel
import com.gt.entrypad.module.project.ui.view.BottomSheetOptionsDialog
import com.gt.entrypad.module.project.ui.view.titleView.TitleViewViewModel
import com.zx.zxutils.views.PhotoPicker.ZXPhotoPreview

class PhotoViewViewModel(var url:String="") :BaseCustomViewModel(){
    fun onClick(view: View){
        if (url.isEmpty()&&resId!=0){
           RxManager().post("show",resId)
        }else{
            RxManager().post("preview",url)
        }

    }
}