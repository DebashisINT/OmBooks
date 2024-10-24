package com.breezefieldombooks.features.newcollectionreport

import com.breezefieldombooks.features.photoReg.model.UserListResponseModel

interface PendingCollListner {
    fun getUserInfoOnLick(obj: PendingCollData)
}