package com.breezefieldombooks.features.stockAddCurrentStock.api

import com.breezefieldombooks.base.BaseResponse
import com.breezefieldombooks.features.location.model.ShopRevisitStatusRequest
import com.breezefieldombooks.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.breezefieldombooks.features.stockAddCurrentStock.ShopAddCurrentStockRequest
import com.breezefieldombooks.features.stockAddCurrentStock.model.CurrentStockGetData
import com.breezefieldombooks.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class ShopAddStockRepository (val apiService : ShopAddStockApi){
    fun shopAddStock(shopAddCurrentStockRequest: ShopAddCurrentStockRequest?): Observable<BaseResponse> {
        return apiService.submShopAddStock(shopAddCurrentStockRequest)
    }

    fun getCurrStockList(sessiontoken: String, user_id: String, date: String): Observable<CurrentStockGetData> {
        return apiService.getCurrStockListApi(sessiontoken, user_id, date)
    }

}