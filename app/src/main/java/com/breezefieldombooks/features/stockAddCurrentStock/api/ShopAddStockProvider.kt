package com.breezefieldombooks.features.stockAddCurrentStock.api

import com.breezefieldombooks.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.breezefieldombooks.features.location.shopRevisitStatus.ShopRevisitStatusRepository

object ShopAddStockProvider {
    fun provideShopAddStockRepository(): ShopAddStockRepository {
        return ShopAddStockRepository(ShopAddStockApi.create())
    }
}