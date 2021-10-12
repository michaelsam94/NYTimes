package com.example.nytimes.domain.repository

import com.example.nytimes.data.remote.dto.MostViewDto
import io.reactivex.Single

interface NyRepository {

    fun getMostViewed() : Single<MostViewDto>
}