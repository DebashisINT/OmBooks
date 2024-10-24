package com.breezefieldombooks.features.survey.api

import com.breezefieldombooks.features.photoReg.api.GetUserListPhotoRegApi
import com.breezefieldombooks.features.photoReg.api.GetUserListPhotoRegRepository

object SurveyDataProvider{

    fun provideSurveyQ(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.create())
    }

    fun provideSurveyQMultiP(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.createImage())
    }
}