package com.breezefieldombooks.features.viewAllOrder.model

import com.breezefieldombooks.app.domain.NewOrderColorEntity
import com.breezefieldombooks.app.domain.NewOrderGenderEntity
import com.breezefieldombooks.app.domain.NewOrderProductEntity
import com.breezefieldombooks.app.domain.NewOrderSizeEntity
import com.breezefieldombooks.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

