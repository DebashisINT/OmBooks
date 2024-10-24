package com.breezefieldombooks.features.viewAllOrder.interf

import com.breezefieldombooks.app.domain.NewOrderColorEntity
import com.breezefieldombooks.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}