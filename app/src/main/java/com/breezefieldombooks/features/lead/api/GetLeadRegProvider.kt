package com.breezefieldombooks.features.lead.api

import com.breezefieldombooks.features.NewQuotation.api.GetQuotListRegRepository
import com.breezefieldombooks.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}