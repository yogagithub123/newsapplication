package com.yogesh.newsapp.ui.topheadlines


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogesh.newsapp.data.model.Article
import com.yogesh.newsapp.data.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.yogesh.newsapp.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
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

    private fun fetchNewsByQuery(query: String) {
        /* viewModelScope.launch {
             query.debounce(3000)
                 .filter { query ->
                     if (query.isEmpty()) {
                         _uiState.value = UiState.Error("Empty Search")
                         return@filter false
                     } else {
                         return@filter true
                     }
                 }
                 .distinctUntilChanged()
                 .flatMapLatest { query ->
                     topHeadlineRepository.getHeadlinesByQuery(query)
                         .catch { e ->
                             _uiState.value = UiState.Error(e.toString())
                         }
                 }
                 .flowOn(Dispatchers.Default)
                 .collect { result ->
                     _uiState.value = UiState.Success(result)
                 }
         }*/

        viewModelScope.launch {
            _uiState.value = UiState.Loading

            topHeadlineRepository.getHeadlinesByQuery(query)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())  // Show error state
                }
                .flowOn(Dispatchers.IO)  // Use IO for network operations
                .collect { result ->
                    _uiState.value = UiState.Success(result)  // Update with result
                }
        }
    }



    fun observeQueryChanges(query: StateFlow<String>) {
        viewModelScope.launch { query
                .debounce(1000)
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    _uiState.value = UiState.Loading
                    topHeadlineRepository.getHeadlinesByQuery(query)
                        .catch { e ->
                            _uiState.value = UiState.Error(e.toString())
                        }
                }
                .flowOn(Dispatchers.Default)
                .collect { result ->
                    _uiState.value = UiState.Success(result)
                }
        }
    }

}