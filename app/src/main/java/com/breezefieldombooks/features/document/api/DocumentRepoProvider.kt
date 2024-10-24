package com.breezefieldombooks.features.document.api

import com.breezefieldombooks.features.dymanicSection.api.DynamicApi
import com.breezefieldombooks.features.dymanicSection.api.DynamicRepo

object DocumentRepoProvider {
    fun documentRepoProvider(): DocumentRepo {
        return DocumentRepo(DocumentApi.create())
    }

    fun documentRepoProviderMultipart(): DocumentRepo {
        return DocumentRepo(DocumentApi.createImage())
    }
}