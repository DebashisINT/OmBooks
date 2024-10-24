package com.breezefieldombooks.features.viewAllOrder.interf

import com.breezefieldombooks.app.domain.NewOrderGenderEntity
import com.breezefieldombooks.features.viewAllOrder.model.ProductOrder
import java.text.FieldPosition

interface NewOrderSizeQtyDelOnClick {
    fun sizeQtySelListOnClick(product_size_qty: ArrayList<ProductOrder>)
    fun sizeQtyListOnClick(product_size_qty: ProductOrder,position: Int)
}