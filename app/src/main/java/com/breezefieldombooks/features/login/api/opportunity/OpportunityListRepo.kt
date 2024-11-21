package com.breezefieldombooks.features.login.api.opportunity

import com.breezefieldombooks.app.Pref
import com.breezefieldombooks.app.utils.AppUtils
import com.breezefieldombooks.base.BaseResponse
import com.breezefieldombooks.features.addshop.model.AudioFetchDataCLass
import com.breezefieldombooks.features.addshop.model.LoanDetailFetchListsResponse
import com.breezefieldombooks.features.addshop.model.LoanDispositionListsResponse
import com.breezefieldombooks.features.addshop.model.LoanFinalStatusListsResponse
import com.breezefieldombooks.features.addshop.model.LoanRiskTypeListsResponse
import com.breezefieldombooks.features.addshop.model.StockAllResponse
import com.breezefieldombooks.features.dashboard.presentation.DashboardActivity
import com.breezefieldombooks.features.login.model.opportunitymodel.OpportunityStatusListResponseModel
import com.breezefieldombooks.features.login.model.productlistmodel.ProductListResponseModel
import com.breezefieldombooks.features.orderITC.SyncDeleteOppt
import com.breezefieldombooks.features.orderITC.SyncEditOppt
import com.breezefieldombooks.features.orderITC.SyncOppt
import com.breezefieldombooks.features.orderList.model.OpportunityListResponseModel
import io.reactivex.Observable
import timber.log.Timber

/**
 * Created by Saikat on 20-11-2018.
 */
class OpportunityListRepo(val apiService: OpportunityListApi) {
    fun getOpportunityStatus(session_token: String): Observable<OpportunityStatusListResponseModel> {
        return apiService.getOpportunityStatusList(session_token)
    }

    fun saveOpportunity(syncOppt: SyncOppt): Observable<BaseResponse> {
        return apiService.saveOpportunity(syncOppt)
    }

    fun editOpportunity(syncEditOppt: SyncEditOppt): Observable<BaseResponse> {
        return apiService.editOpportunity(syncEditOppt)
    }
    fun deleteOpportunity(syncDeleteOppt: SyncDeleteOppt): Observable<BaseResponse> {
        return apiService.deleteOpportunity(syncDeleteOppt)
    }
    fun getOpportunityL(user_id: String): Observable<OpportunityListResponseModel> {
        return apiService.getOpportunityL(user_id)
    }

    fun getAudioL(user_id: String,data_limit_in_days:String): Observable<AudioFetchDataCLass> {
        return apiService.getAudioLApi(user_id,data_limit_in_days)
    }


    fun saveLMSModuleInfo(obj: DashboardActivity.LMSModule): Observable<BaseResponse> {
        return apiService.saveLMSModuleInfoApi(obj)
    }

    fun getAllStock(user_id: String): Observable<StockAllResponse> {
        return apiService.getAllStockApi(user_id)
    }

    fun getLoanRiskTypeLists(user_id: String): Observable<LoanRiskTypeListsResponse> {
        return apiService.getLoanRiskTypeLists(user_id)
    }

    fun getLoanDispositionLists(user_id: String): Observable<LoanDispositionListsResponse> {
        return apiService.getLoanDispositionLists(user_id)
    }

    fun getLoanFinalStatusLists(user_id: String): Observable<LoanFinalStatusListsResponse> {
        return apiService.getLoanFinalStatusLists(user_id)
    }

    fun getLoanDetailFetch(user_id: String): Observable<LoanDetailFetchListsResponse> {
        return apiService.getLoanDetailFetch(user_id)
    }
}