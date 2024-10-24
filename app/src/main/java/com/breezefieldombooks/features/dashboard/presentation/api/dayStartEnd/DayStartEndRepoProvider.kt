package com.breezefieldombooks.features.dashboard.presentation.api.dayStartEnd

import com.breezefieldombooks.features.stockCompetetorStock.api.AddCompStockApi
import com.breezefieldombooks.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}