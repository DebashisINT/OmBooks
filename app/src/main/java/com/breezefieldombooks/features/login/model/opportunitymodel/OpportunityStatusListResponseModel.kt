package com.breezefieldombooks.features.login.model.opportunitymodel

import com.breezefieldombooks.app.domain.OpportunityStatusEntity
import com.breezefieldombooks.app.domain.ProductListEntity
import com.breezefieldombooks.base.BaseResponse

/**
 * Created by Puja on 30.05.2024
 */
class OpportunityStatusListResponseModel : BaseResponse() {
    var status_list: ArrayList<OpportunityStatusEntity>? = null
}