package com.breezefieldombooks.features.location.api

import com.breezefieldombooks.features.location.shopdurationapi.ShopDurationApi
import com.breezefieldombooks.features.location.shopdurationapi.ShopDurationRepository


object LocationRepoProvider {
    fun provideLocationRepository(): LocationRepo {
        return LocationRepo(LocationApi.create())
    }
}