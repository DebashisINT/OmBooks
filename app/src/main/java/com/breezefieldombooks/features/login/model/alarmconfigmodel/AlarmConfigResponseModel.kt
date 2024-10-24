package com.breezefieldombooks.features.login.model.alarmconfigmodel

import com.breezefieldombooks.base.BaseResponse

/**
 * Created by Saikat on 19-02-2019.
 */
class AlarmConfigResponseModel : BaseResponse() {
    var alarm_settings_list: ArrayList<AlarmConfigDataModel>? = null
}