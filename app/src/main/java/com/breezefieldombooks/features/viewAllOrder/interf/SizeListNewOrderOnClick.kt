package com.breezefieldombooks.features.viewAllOrder.interf

import com.breezefieldombooks.app.domain.NewOrderProductEntity
import com.breezefieldombooks.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}