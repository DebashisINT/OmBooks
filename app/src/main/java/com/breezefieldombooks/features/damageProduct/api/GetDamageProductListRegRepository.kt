package com.breezefieldombooks.features.damageProduct.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import com.breezefieldombooks.app.FileUtils
import com.breezefieldombooks.base.BaseResponse
import com.breezefieldombooks.features.NewQuotation.model.*
import com.breezefieldombooks.features.addshop.model.AddShopRequestData
import com.breezefieldombooks.features.addshop.model.AddShopResponse
import com.breezefieldombooks.features.damageProduct.model.DamageProductResponseModel
import com.breezefieldombooks.features.damageProduct.model.delBreakageReq
import com.breezefieldombooks.features.damageProduct.model.viewAllBreakageReq
import com.breezefieldombooks.features.login.model.userconfig.UserConfigResponseModel
import com.breezefieldombooks.features.myjobs.model.WIPImageSubmit
import com.breezefieldombooks.features.photoReg.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class GetDamageProductListRegRepository(val apiService : GetDamageProductListApi) {

    fun viewBreakage(req: viewAllBreakageReq): Observable<DamageProductResponseModel> {
        return apiService.viewBreakage(req)
    }

    fun delBreakage(req: delBreakageReq): Observable<BaseResponse>{
        return apiService.BreakageDel(req.user_id!!,req.breakage_number!!,req.session_token!!)
    }

}