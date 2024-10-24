package com.breezefieldombooks.features.activities.api

import com.breezefieldombooks.features.member.api.TeamApi
import com.breezefieldombooks.features.member.api.TeamRepo

object ActivityRepoProvider {
    fun activityRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.create())
    }

    fun activityImageRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.createImage())
    }
}