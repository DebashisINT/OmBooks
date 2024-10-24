package com.breezefieldombooks.features.newcollection.model

import com.breezefieldombooks.app.domain.CollectionDetailsEntity
import com.breezefieldombooks.base.BaseResponse
import com.breezefieldombooks.features.shopdetail.presentation.model.collectionlist.CollectionListDataModel

/**
 * Created by Saikat on 15-02-2019.
 */
class NewCollectionListResponseModel : BaseResponse() {
    //var collection_list: ArrayList<CollectionListDataModel>? = null
    var collection_list: ArrayList<CollectionDetailsEntity>? = null
}