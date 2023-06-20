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
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchSoundsUseCase: SearchSoundsUseCase
): ViewModel(){

    private val searchValue = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    private val _songsUiState = MutableStateFlow(
        SearchUiState(
            onSearchChanged = this::searchSongs,
        )
    )

    val songsUiState = _songsUiState.asStateFlow()



     fun searchSongs(query: String) {
        searchValue.value = query
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val songFlow: Flow<PagingData<Song>> = searchValue.flatMapLatest { searchvalue ->
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SongPagingSource(
                    searchSoundsUseCase,
                    searchvalue,
                    _isLoading
                )
            }
        ).flow
    }.cachedIn(viewModelScope)



    companion object {
        private const val PAGE_SIZE = 6
    }

}

