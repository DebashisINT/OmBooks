package com.breezefieldombooks.features.orderList.model

import com.breezefieldombooks.base.BaseResponse


class ReturnListResponseModel: BaseResponse() {
    var return_list: ArrayList<ReturnDataModel>? = null
}