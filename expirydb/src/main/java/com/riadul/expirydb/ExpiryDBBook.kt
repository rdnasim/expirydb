package com.riadul.expirydb

import io.paperdb.Paper
import java.util.Calendar

/**
 * ExpiryDBBook is a class that provides storage operations with expiry time for objects using Paper library.
 */

class ExpiryDBBook {
    private val book = Paper.book()

    /**
     * Saves an object with expiry time in Book storage.
     *
     * @param key              object key used as part of the object's file name
     * @param value            object to save, must have a no-arg constructor, and cannot be null
     * @param expiryTimeMillis expiry time in milliseconds
     */
    fun <T> write(key: String, value: T, expiryTimeMillis: Long) {
        setLocalStorageWithExpiry(key, value, expiryTimeMillis)
    }

    /**
     * Retrieves an object from storage.
     *
     * @param key object key to read
     * @return the saved object instance, or null if the object has expired or does not exist
     */
    fun read(key: String): Any? {
        return getLocalStorageWithExpiry(key)
    }

    /**
     * Deletes an object from storage.
     *
     * @param key object key to delete
     */
    fun delete(key: String) {
        book.delete(key)
    }

    /**
     * Deletes all objects in the storage.
     */
    fun destroy() {
        book.destroy()
    }

    /**
     * Retrieves all the keys of the objects in the storage.
     *
     * @return a list of all keys in the storage
     */
    fun getAllKeys(): List<String> {
        return book.allKeys
    }

    private fun getLocalStorageWithExpiry(key: String): Any? {
        val item = book.read<LocalStorageItem>(key)
        if (item != null) {
            val now = Calendar.getInstance().timeInMillis

            if (now > item.expiry) {
                book.delete(key)
                return null
            }
            return item.value
        }

        return null
    }

    private fun <T> setLocalStorageWithExpiry(key: String, value: T, expiryTimeMillis: Long) {
        val now = Calendar.getInstance().timeInMillis
        val expiryTime = now + expiryTimeMillis

        val item = LocalStorageItem(value, expiryTime)

        book.write(key, item)
    }

    /**
     * Data class representing an item in the storage, including its value and expiry time.
     *
     * @param value  the value of the object
     * @param expiry the expiry time of the object
     */
    data class LocalStorageItem(
        val value: Any?,
        val expiry: Long
    )
}
