package com.breezefieldombooks.features.viewAllOrder.interf

import com.breezefieldombooks.app.domain.NewOrderGenderEntity
import com.breezefieldombooks.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}