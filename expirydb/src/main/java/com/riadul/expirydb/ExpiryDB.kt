package com.riadul.expirydb

import android.content.Context
import io.paperdb.Paper

/**
 * ExpiryDB is a utility object that provides initialization and access to the ExpiryDBBook storage.
 * It is responsible for initializing the Paper library and creating an instance of ExpiryDBBook.
 */
object ExpiryDB {
    private const val TAG = "ExpiryDB"

    /**
     * Initializes the ExpiryDB library with the provided context.
     *
     * @param context the application context
     */
    fun init(context: Context) {
        Paper.init(context)
    }

    /**
     * Returns an instance of ExpiryDBBook for performing storage operations.
     *
     * @return an instance of ExpiryDBBook
     */
    fun book(): ExpiryDBBook {
        return ExpiryDBBook()
    }
}
