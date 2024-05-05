package de.dannyb.imnuri.data.utils

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

abstract class BaseContentProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        // no-op
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        // no-op
        return null
    }

    override fun getType(uri: Uri): String? {
        // no-op
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // no-op
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        // no-op
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        // no-op
        return 0
    }

}