package com.example.cleanmusicplayer.ui.screens.search.utils.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.SearchSoundsUseCase
import com.example.domain.model.Song
import kotlinx.coroutines.flow.MutableStateFlow

class SongPagingSource(
    private val searchSoundUseCase: SearchSoundsUseCase,
    private val search: String? = null,
    private val loadingUI: MutableStateFlow<Boolean>
) : PagingSource<Int, Song>() {

    override fun getRefreshKey(state: PagingState<Int, Song>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Song> {
        loadingUI.emit(true)
        return try {
            val page = params.key ?: 1
            val pageSize = 5
            val response = searchSoundUseCase.execute(
                page = page,
                pageSize = pageSize,
                search = search
            )
            loadingUI.emit(false)
            LoadResult.Page(
                 data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
