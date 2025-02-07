package com.yogesh.newsapp.ui.topheadlines


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogesh.newsapp.data.model.Article
import com.yogesh.newsapp.data.repository.TopHeadlineRepository
import com.yogesh.newsapp.utils.Constant.COUNTRY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.yogesh.newsapp.ui.common.UiState
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository):ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

   /* init {
        fetchNews()
    }*/

     fun fetchNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines("us")
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
    fun fetchNewsByResources(source:String) {
        viewModelScope.launch {
            topHeadlineRepository.getHeadlinesByResources(source)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}