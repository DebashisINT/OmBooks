package com.breezefieldombooks.features.login.model.productlistmodel

import com.breezefieldombooks.app.domain.ModelEntity
import com.breezefieldombooks.app.domain.ProductListEntity
import com.breezefieldombooks.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}