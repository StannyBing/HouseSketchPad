package com.gt.entrypad.api

import com.frame.zxmvp.basebean.BaseRespose
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable


interface ApiService {
    //身份证识别
    @POST("https://ocrapi-idcard.taobao.com/ocrservice/idcard")
    @Headers("Content-type:application/json;charset=UTF-8","Authorization:APPCODE 950a3001439c48349eea2e8d70fcf1bb")
    fun scanIdCard(@Body body:RequestBody): Observable<BaseRespose<String>>
}