package com.breezefieldombooks.features.menuBeat

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.breezefieldombooks.app.FileUtils
import com.breezefieldombooks.app.Pref
import com.breezefieldombooks.base.BaseResponse
import com.breezefieldombooks.features.addshop.model.*
import com.breezefieldombooks.features.addshop.model.assigntopplist.AddShopUploadImg
import com.breezefieldombooks.features.addshop.model.assigntopplist.AddshopImageMultiReqbody1
import com.breezefieldombooks.features.addshop.presentation.ShopListSubmitResponse
import com.breezefieldombooks.features.addshop.presentation.multiContactRequestData
import com.breezefieldombooks.features.beatCustom.BeatGetStatusModel
import com.breezefieldombooks.features.dashboard.presentation.DashboardActivity
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by saheli on 16-12-2023.
 */
class MenuBeatRepository(val apiService: MenuBeatApi) {

    fun currentTabMenubeat(sessiontoken: String, user_id: String, beat_id: String): Observable<MenuBeatResponse> {
        return apiService.getCurrentTabData(user_id,sessiontoken,beat_id)
    }
    fun hirerchyTabMenubeat(sessiontoken: String, user_id: String): Observable<MenuBeatAreaRouteResponse> {
        return apiService.getHirerchyTabData(user_id,sessiontoken)
    }

}