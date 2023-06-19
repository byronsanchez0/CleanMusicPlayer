package com.example.cleanmusicplayer.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cleanmusicplayer.ui.screens.search.utils.paging.SongPagingSource
import com.example.domain.SearchSoundsUseCase
import com.example.domain.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchSoundsUseCase: SearchSoundsUseCase
): ViewModel(){

    private val _search = MutableStateFlow<String?>("")
//    private val search : StateFlow<String> = _search
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _uiSearchState = MutableStateFlow(
        SearchUiState(
            onSearchChanged = this::search,
        )
    )

    val uiSearchState = _uiSearchState.asStateFlow()


    private fun search(query: String) {
        viewModelScope.launch {
            _search.value = query
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val songFlow: Flow<PagingData<Song>> = _search.flatMapLatest {
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SongPagingSource(
                    searchSoundsUseCase,
                    it,
                    _isLoading
                )
            }
        ).flow
    }.cachedIn(viewModelScope)

//    val flow = Pager(
//        // Configure how data is loaded by passing additional properties to
//        // PagingConfig, such as prefetchDistance.
//        PagingConfig(pageSize = 10)
//    ) {
//        SongPagingSource(searchSoundsUseCase, _search.value.toString(), _isLoading)
//    }.flow
//        .cachedIn(viewModelScope)





    private fun createPaging(
        search: String?
    ): Pager<Int, Song> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SongPagingSource(
                    searchSoundsUseCase,
                    search,
                    _isLoading
                )
            }
        )
    }
    companion object {
        private const val PAGE_SIZE = 10
    }

}

