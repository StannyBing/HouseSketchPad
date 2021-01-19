package com.gt.entrypad.base.view

import com.gt.entrypad.base.viewModel.BaseCustomViewModel

interface ICustomView <S: BaseCustomViewModel>{
    fun setData(data:S)
    fun setStyle(resId:Int)
    fun setActionListener(listener: ICustomViewActionListener)
}