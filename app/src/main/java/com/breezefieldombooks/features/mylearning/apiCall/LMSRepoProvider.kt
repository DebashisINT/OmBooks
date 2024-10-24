package com.breezefieldombooks.features.mylearning.apiCall

import com.breezefieldombooks.features.login.api.opportunity.OpportunityListApi
import com.breezefieldombooks.features.login.api.opportunity.OpportunityListRepo

object LMSRepoProvider {
    fun getTopicList(): LMSRepo {
        return LMSRepo(LMSApi.create())
    }
}