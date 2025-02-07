package com.yogesh.newsapp.ui.sources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogesh.newsapp.data.model.Sources
import com.yogesh.newsapp.data.repository.SourcesRepository
import com.yogesh.newsapp.di.ActivityScope
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.yogesh.newsapp.ui.common.UiState

@ActivityScope
class SourcesViewModel @Inject constructor(private val sourcesRepository: SourcesRepository):ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Sources>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Sources>>> = _uiState

    init {
        fetchSources()
    }

    private fun fetchSources() {
        viewModelScope.launch {
            sourcesRepository.getSources()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}