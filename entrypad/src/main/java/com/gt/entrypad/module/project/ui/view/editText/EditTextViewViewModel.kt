package com.gt.entrypad.module.project.ui.view.editText

import android.text.Editable
import android.text.TextWatcher
import com.gt.entrypad.base.viewModel.BaseCustomViewModel

class EditTextViewViewModel (var title:String="",var hint:String="",var inputContent:String="",var isRequired:Boolean=false,var requiredContent:String=""):BaseCustomViewModel(){

    var textWatcher = object :TextWatcher{
       override fun afterTextChanged(p0: Editable?) {
           inputContent = p0?.toString()?.trim()?:""
       }

       override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
       }

       override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
       }

   }
}