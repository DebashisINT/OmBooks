package com.breezefieldombooks.features.login.model.productlistmodel

import com.breezefieldombooks.app.domain.NewOrderScrOrderEntity
import com.breezefieldombooks.app.domain.ProductListEntity

class NewOdrScrOrderListModel {
    var status:String? = null
    var message:String? = null
    var user_id:String? = null
    var order_list: ArrayList<NewOrderScrOrderEntity>? = null
}