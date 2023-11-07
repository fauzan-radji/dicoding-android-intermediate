package com.dicoding.myunlimitedquotes.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.dicoding.myunlimitedquotes.database.QuoteDatabase
import com.dicoding.myunlimitedquotes.network.ApiService
import com.dicoding.myunlimitedquotes.network.QuoteResponseItem
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Test

@ExperimentalPagingApi
class QuoteRemoteMediatorTest {
    private var mockApi: ApiService = FakeApiService()
    private var mockDb: QuoteDatabase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        QuoteDatabase::class.java
    ).allowMainThreadQueries().build()

    @Test
    fun refreshLoadReturnsSuccessResultWenMoreDataIsPresent() = runTest {
        val remoteMediator = QuoteRemoteMediator(
            mockDb,
            mockApi
        )
        val pagingState = PagingState<Int, QuoteResponseItem>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @After
    fun tearDown() {
        mockDb.clearAllTables()
    }
}

class FakeApiService: ApiService {
    override suspend fun getQuote(page: Int, size: Int): List<QuoteResponseItem> {
        val items: MutableList<QuoteResponseItem> = arrayListOf()
        for (i in 0..100) {
            val quote = QuoteResponseItem(
                i.toString(),
                "Author $i",
                "Quote $i"
            )
            items.add(quote)
        }

        return items.subList((page - 1) * size, (page - 1) * size + size)
    }
}