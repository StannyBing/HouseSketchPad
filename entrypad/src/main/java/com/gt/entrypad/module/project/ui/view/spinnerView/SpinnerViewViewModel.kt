package com.gt.entrypad.module.project.ui.view.spinnerView

import com.gt.entrypad.base.viewModel.BaseCustomViewModel
import com.zx.zxutils.entity.KeyValueEntity

class SpinnerViewViewModel(var title:String="",var inputContent:String="",var isRequired:Boolean=false,var requiredContent:String="",var entries:ArrayList<KeyValueEntity>) :BaseCustomViewModel()