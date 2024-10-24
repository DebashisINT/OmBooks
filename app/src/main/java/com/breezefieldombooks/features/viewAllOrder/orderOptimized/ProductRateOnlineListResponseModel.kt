package com.breezefieldombooks.features.viewAllOrder.orderOptimized

import com.breezefieldombooks.app.domain.ProductOnlineRateTempEntity
import com.breezefieldombooks.base.BaseResponse
import com.breezefieldombooks.features.login.model.productlistmodel.ProductRateDataModel
import java.io.Serializable

class ProductRateOnlineListResponseModel: BaseResponse(), Serializable {
    var product_rate_list: ArrayList<ProductOnlineRateTempEntity>? = null
}