package com.breezefieldombooks.features.weather.api

import com.breezefieldombooks.base.BaseResponse
import com.breezefieldombooks.features.task.api.TaskApi
import com.breezefieldombooks.features.task.model.AddTaskInputModel
import com.breezefieldombooks.features.weather.model.ForeCastAPIResponse
import com.breezefieldombooks.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}