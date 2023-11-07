package com.dicoding.myunlimitedquotes

import com.dicoding.myunlimitedquotes.network.QuoteResponseItem

object DataDummy {

    fun generateDummyQuoteResponse(): List<QuoteResponseItem> {
        val items: MutableList<QuoteResponseItem> = arrayListOf()
        for (i in 0..100) {
            val quote = QuoteResponseItem(
                i.toString(),
                "Author $i",
                "Quote $i"
            )
            items.add(quote)
        }

        return items
    }
}