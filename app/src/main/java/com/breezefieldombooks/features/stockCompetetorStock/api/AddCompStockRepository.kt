package com.breezefieldombooks.features.stockCompetetorStock.api

import com.breezefieldombooks.base.BaseResponse
import com.breezefieldombooks.features.orderList.model.NewOrderListResponseModel
import com.breezefieldombooks.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.breezefieldombooks.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class AddCompStockRepository(val apiService:AddCompStockApi){

    fun addCompStock(shopAddCompetetorStockRequest: ShopAddCompetetorStockRequest): Observable<BaseResponse> {
        return apiService.submShopCompStock(shopAddCompetetorStockRequest)
    }

    fun getCompStockList(sessiontoken: String, user_id: String, date: String): Observable<CompetetorStockGetData> {
        return apiService.getCompStockList(sessiontoken, user_id, date)
    }
}