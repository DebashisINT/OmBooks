package com.breezefieldombooks.features.nearbyuserlist.api

import com.breezefieldombooks.app.Pref
import com.breezefieldombooks.features.nearbyuserlist.model.NearbyUserResponseModel
import com.breezefieldombooks.features.newcollection.model.NewCollectionListResponseModel
import com.breezefieldombooks.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}