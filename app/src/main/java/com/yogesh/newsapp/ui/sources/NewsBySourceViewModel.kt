package com.yogesh.newsapp.ui.sources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogesh.newsapp.data.model.Article
import com.yogesh.newsapp.data.model.Sources
import com.yogesh.newsapp.data.repository.SourceNewsRepository
import com.yogesh.newsapp.ui.common.UiState
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsBySourceViewModel @Inject constructor(private val sourceNewsRepository: SourceNewsRepository):ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

   fun fetchNewsByResources(source:String)
    {
        viewModelScope.launch {
            sourceNewsRepository.getNewsByResources(source).
                    catch { e ->
                        _uiState.value=UiState.Error(e.toString())
                    }.collect{
                        _uiState.value=UiState.Success(it)
            }
        }
    }
}