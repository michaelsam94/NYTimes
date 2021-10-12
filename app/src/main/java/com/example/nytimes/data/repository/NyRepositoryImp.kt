package com.example.nytimes.data.repository

import com.example.nytimes.data.remote.NYApi
import com.example.nytimes.data.remote.dto.MostViewDto
import com.example.nytimes.domain.repository.NyRepository
import io.reactivex.Single
import javax.inject.Inject

class NyRepositoryImp @Inject constructor(val api: NYApi) : NyRepository {


    override fun getMostViewed(): Single<MostViewDto> {
        return api.getMostViewed()
    }
}