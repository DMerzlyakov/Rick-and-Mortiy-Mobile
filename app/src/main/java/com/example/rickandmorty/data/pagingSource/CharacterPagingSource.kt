package com.example.rickandmorty.data.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.domain.Character

typealias CharactersPageLoader = suspend (pageIndex: Int, pageSize: Int) -> List<Character>

class CharacterPagingSource(
    private val loader: CharactersPageLoader,
    private val pageSize: Int
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        // get the index of page to be loaded (it may be NULL, in this case let's load the first page with index = 0)
        val pageIndex = params.key ?: 1

        return try {
            // loading the desired page of
            val users = loader.invoke(pageIndex, params.loadSize)
            // success! now we can return LoadResult.Page
            return LoadResult.Page(
                data = users,
                // index of the previous page if exists
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                // index of the next page if exists;
                // please note that 'params.loadSize' may be larger for the first load (by default x3 times)
                nextKey = if (users.isEmpty()) null else pageIndex + (params.loadSize / pageSize)
            )
        } catch (e: Exception) {
            // failed to load users -> need to return LoadResult.Error
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        // get the most recently accessed index in the users list:
        val anchorPosition = state.anchorPosition ?: return null
        // convert item index to page index:
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        Log.e("CALCULATE", page.prevKey?.plus(1).toString() + page.nextKey?.minus(1).toString())
        // page doesn't have 'currentKey' property, so need to calculate it manually:
        return page.prevKey?.plus(1) ?: 1
//        return 1
    }

}