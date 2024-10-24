package com.breezefieldombooks.features.leaderboard.api

import com.breezefieldombooks.app.NetworkConstant
import com.breezefieldombooks.base.BaseResponse
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
import com.breezefieldombooks.features.login.model.WhatsappApiData
import com.breezefieldombooks.features.login.model.WhatsappApiFetchData
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by Puja on 12-40-2014.
 */
interface LeaderboardApi {

    @FormUrlEncoded
    @POST("LeaderboardInfo/LeaderboardBranchLists")
    fun branchList(@Field("session_token") session_token: String): Observable<LeaderboardBranchData>

    @FormUrlEncoded
    @POST("LeaderboardInfo/LeaderboardOwnList")
    fun ownDatalist(@Field("user_id") user_id: String,@Field("activitybased") activitybased: String,@Field("branchwise") branchwise: String,@Field("flag") flag: String): Observable<LeaderboardOwnData>

    @FormUrlEncoded
    @POST("LeaderboardInfo/LeaderboardOverallList")
    fun overAllDatalist(@Field("user_id") user_id: String,@Field("activitybased") activitybased: String,@Field("branchwise") branchwise: String,@Field("flag") flag: String): Observable<LeaderboardOverAllData>


    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): LeaderboardApi {
            val retrofit = Retrofit.Builder()
                    .client(NetworkConstant.setTimeOut())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(NetworkConstant.ADD_SHOP_BASE_URL)
                    .build()

            return retrofit.create(LeaderboardApi::class.java)
        }

        fun createWithoutMultipart(): LeaderboardApi {
            val retrofit = Retrofit.Builder()
                    .client(NetworkConstant.setTimeOut())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(NetworkConstant.BASE_URL)
                    .build()

            return retrofit.create(LeaderboardApi::class.java)
        }
    }
}