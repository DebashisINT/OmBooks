package com.breezefieldombooks.features.weather.api

import com.breezefieldombooks.features.task.api.TaskApi
import com.breezefieldombooks.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}