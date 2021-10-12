package com.example.nytimes.domain.usecase

import com.example.nytimes.data.remote.dto.toMostViewed
import com.example.nytimes.domain.model.MostViewed
import com.example.nytimes.domain.repository.NyRepository
import com.example.nytimes.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMostViewedUseCase @Inject constructor(private val repo: NyRepository) :
    SingleUseCase<List<MostViewed>>() {


    override fun buildSingle(): Single<List<MostViewed>> {
        return repo.getMostViewed()
            .map { mostViewDto -> mostViewDto.results.map { it.toMostViewed() } }
    }


}