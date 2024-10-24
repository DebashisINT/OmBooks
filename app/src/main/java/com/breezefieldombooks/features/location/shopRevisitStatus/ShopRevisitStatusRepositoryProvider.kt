package com.breezefieldombooks.features.location.shopRevisitStatus

import com.breezefieldombooks.features.location.shopdurationapi.ShopDurationApi
import com.breezefieldombooks.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}