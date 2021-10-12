package com.example.nytimes.data.remote

import com.example.nytimes.data.remote.dto.MostViewDto
import io.reactivex.Single
import retrofit2.http.GET

interface NYApi {

    @GET("mostviewed/all-sections/7.json")
    fun getMostViewed() : Single<MostViewDto>
}