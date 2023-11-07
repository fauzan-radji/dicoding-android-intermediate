package com.dicoding.myunlimitedquotes.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.myunlimitedquotes.network.QuoteResponseItem

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<QuoteResponseItem>)

    @Query("SELECT * FROM quote")
    fun getAllQuotes(): PagingSource<Int, QuoteResponseItem>

    @Query("DELETE FROM quote")
    suspend fun clearAll()
}