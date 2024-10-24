package com.breezefieldombooks.features.photoReg.present

import com.breezefieldombooks.app.domain.ProspectEntity
import com.breezefieldombooks.features.photoReg.model.UserListResponseModel

interface DsStatusListner {
    fun getDSInfoOnLick(obj: ProspectEntity)
}