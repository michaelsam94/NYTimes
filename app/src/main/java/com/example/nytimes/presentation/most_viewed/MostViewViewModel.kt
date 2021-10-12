package com.example.nytimes.presentation.most_viewed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nytimes.common.Resource
import com.example.nytimes.domain.model.MostViewed
import com.example.nytimes.domain.repository.NyRepository
import com.example.nytimes.domain.usecase.GetMostViewedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MostViewViewModel @Inject constructor(private val useCase: GetMostViewedUseCase) :
    ViewModel() {

    private val _state = MutableLiveData<Resource<List<MostViewed>>>()
    val state = _state

    init {
        getMostViewed()
    }

    fun getMostViewed() {
        _state.value = Resource.Loading()
        useCase.execute({
            _state.value = Resource.Success(it)
        }, {
            _state.value = Resource.Error(message = it.localizedMessage ?: "uncaught exception",data = null)
        })
    }

}