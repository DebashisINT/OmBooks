package com.breezefieldombooks.features.viewAllOrder.interf

import com.breezefieldombooks.app.domain.NewOrderGenderEntity
import com.breezefieldombooks.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}