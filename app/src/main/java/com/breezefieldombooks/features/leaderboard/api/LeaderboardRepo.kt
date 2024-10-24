package com.breezefieldombooks.features.leaderboard.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import com.fasterxml.jackson.databind.ObjectMapper
import com.breezefieldombooks.app.FileUtils
import com.breezefieldombooks.app.Pref
import com.breezefieldombooks.base.BaseResponse
import com.breezefieldombooks.features.addshop.model.AddLogReqData
import com.breezefieldombooks.features.addshop.model.AddShopRequestData
import com.breezefieldombooks.features.addshop.model.AddShopResponse
import com.breezefieldombooks.features.addshop.model.LogFileResponse
import com.breezefieldombooks.features.addshop.model.UpdateAddrReq
import com.breezefieldombooks.features.contacts.CallHisDtls
import com.breezefieldombooks.features.contacts.CompanyReqData
import com.breezefieldombooks.features.contacts.ContactMasterRes
import com.breezefieldombooks.features.contacts.SourceMasterRes
import com.breezefieldombooks.features.contacts.StageMasterRes
import com.breezefieldombooks.features.contacts.StatusMasterRes
import com.breezefieldombooks.features.contacts.TypeMasterRes
import com.breezefieldombooks.features.dashboard.presentation.DashboardActivity
import com.breezefieldombooks.features.login.model.WhatsappApiData
import com.breezefieldombooks.features.login.model.WhatsappApiFetchData
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by Puja on 10-10-2024.
 */
class LeaderboardRepo(val apiService: LeaderboardApi) {

    fun branchlist(session_token: String): Observable<LeaderboardBranchData> {
        return apiService.branchList(session_token)
    }
    fun ownDatalist(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOwnData> {
        return apiService.ownDatalist(user_id,activitybased,branchwise,flag)
    }
    fun overAllAPI(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOverAllData> {
        return apiService.overAllDatalist(user_id,activitybased,branchwise,flag)
    }
}